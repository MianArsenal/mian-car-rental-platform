package com.mian.car.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mian.car.rental.dao.RentalOrderMapper;
import com.mian.car.rental.exception.BizException;
import com.mian.car.rental.po.Car;
import com.mian.car.rental.po.CarModel;
import com.mian.car.rental.po.RentalOrder;
import com.mian.car.rental.po.RentalOrderTimeSlice;
import com.mian.car.rental.service.CarModelService;
import com.mian.car.rental.service.CarService;
import com.mian.car.rental.service.RentalOrderService;
import com.mian.car.rental.service.RentalOrderTimeSliceService;
import com.mian.car.rental.util.DateUtil;
import com.mian.car.rental.vo.CarVo;
import com.mian.car.rental.vo.RentalOrderVo;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mian.car.rental.vo.response.ResponseStatus.DATABASE_INSERT_FAIL;
import static com.mian.car.rental.vo.response.ResponseStatus.DATE_STRING_FORMAT_ERROR;

@Service
@AllArgsConstructor
public class RentalOrderServiceImpl extends ServiceImpl<RentalOrderMapper, RentalOrder> implements RentalOrderService {

    private final CarService carService;
    private final CarModelService carModelService;
    private final RentalOrderTimeSliceService rentalOrderTimeSliceService;
    private final PlatformTransactionManager platformTransactionManager;
    private final TransactionDefinition transactionDefinition;


    @Override
    public RentalOrderVo addRentalOrder(RentalOrderVo rentalOrderVo) {
        RentalOrder rentalOrder = new RentalOrder(rentalOrderVo);
        try {
            CarModel carModel = carModelService.getById(rentalOrderVo.getCarModelId());
            rentalOrder.setTotalCost(getTotalCost(carModel.getPricePerHour(), rentalOrderVo.getStartTimeString(), rentalOrderVo.getEndTimeString()));
            List<Pair<String, String>> timeSlicePairs = DateUtil.getTimeSlicePairs(rentalOrderVo.getStartTimeString(), rentalOrderVo.getEndTimeString());
            List<Car> availableCars = getAvailableCars(rentalOrderVo.getCarModelId(), timeSlicePairs);
            if (CollectionUtils.isEmpty(availableCars)) {
                throw new BizException(DATABASE_INSERT_FAIL, "the car you want to rent is preempted");
            }
            for (int i = 0; i < availableCars.size(); i++) {
                TransactionStatus transactionStatus = null;
                try {
                    rentalOrderVo.setCar(new CarVo(availableCars.get(i)));
                    transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
                    rentalOrder.setCarId(availableCars.get(i).getId());
                    this.save(rentalOrder);
                    rentalOrderTimeSliceService.saveBatch(getRentalOrderTimeSlices(rentalOrder, timeSlicePairs));
                    platformTransactionManager.commit(transactionStatus);
                    break;
                } catch (Exception e) {
                    if (null != transactionStatus) {
                        platformTransactionManager.rollback(transactionStatus);
                    }
                    if (i == availableCars.size()) {
                        throw e;
                    }
                }
            }
        } catch (ParseException e) {
            throw new BizException(DATE_STRING_FORMAT_ERROR, "due to exist invalid date string, failed to add new rental order");
        } catch (DuplicateKeyException e) {
            throw new BizException(DATABASE_INSERT_FAIL, "the car you want to rent is preempted");
        }
        BeanUtils.copyProperties(rentalOrder, rentalOrderVo);
        return rentalOrderVo;
    }

    private List<Car> getAvailableCars(String carModelId, List<Pair<String, String>> timeSlicePairs) {
        List<String> totalSubStartTimeStringList = timeSlicePairs.stream().map(Pair::getKey).collect(Collectors.toList());
        List<RentalOrderTimeSlice> rentalOrderTimeSlices = rentalOrderTimeSliceService.getRentalOrderTimeSlicesByStartTimeList(totalSubStartTimeStringList);
        List<String> carIds = rentalOrderTimeSlices.stream().map(RentalOrderTimeSlice::getCarId).distinct().collect(Collectors.toList());
        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        wrapper.eq(Car.COLUMN_CAR_MODEL_ID, carModelId)
                .notIn(Car.COLUMN_ID, carIds);
        return carService.list(wrapper);
    }


    private List<RentalOrderTimeSlice> getRentalOrderTimeSlices(RentalOrder rentalOrder, List<Pair<String, String>> timeSlicePairs) {
        List<RentalOrderTimeSlice> rentalOrderTimeSlices = new ArrayList<>();
        for (Pair<String, String> timeSlicePair : timeSlicePairs) {
            rentalOrderTimeSlices.add(new RentalOrderTimeSlice() {{
                setRentalOrderId(rentalOrder.getId());
                setStartTimeString(timeSlicePair.getKey());
                setEndTimeString(timeSlicePair.getValue());
                setCarId(rentalOrder.getCarId());
                setUserId(rentalOrder.getUserId());
            }});
        }
        return rentalOrderTimeSlices;
    }

    private Integer getTotalCost(Integer pricePerHour, String startTimeString, String endTimeString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.TIME_STRING_FORMAT);
        int numberOfHours = (int) ((simpleDateFormat.parse(endTimeString).getTime() - simpleDateFormat.parse(startTimeString).getTime()) / DateUtil.NUMBER_OF_MILLISECONDS_PER_HOUR);
        return numberOfHours * pricePerHour;
    }
}
