package com.mian.car.rental.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mian.car.rental.dao.CarModelMapper;
import com.mian.car.rental.exception.BizException;
import com.mian.car.rental.po.CarModel;
import com.mian.car.rental.po.RentalOrderTimeSlice;
import com.mian.car.rental.service.CarModelService;
import com.mian.car.rental.service.RentalOrderTimeSliceService;
import com.mian.car.rental.util.DateUtil;
import com.mian.car.rental.vo.CarModelVo;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mian.car.rental.vo.response.ResponseStatus.DATE_STRING_FORMAT_ERROR;

@Service
@AllArgsConstructor
public class CarModelServiceImpl extends ServiceImpl<CarModelMapper, CarModel> implements CarModelService {

    private final RentalOrderTimeSliceService rentalOrderTimeSliceService;

    @Override
    public List<CarModelVo> getAvailableCarModel(String startTimeString, String endTimeString) {
        List<CarModelVo> result;
        try {
            List<Pair<String, String>> pairList = DateUtil.getTimeSlicePairs(startTimeString, endTimeString);
            List<String> totalSubStartTimeStringList = pairList.stream().map(Pair::getKey).collect(Collectors.toList());
            List<RentalOrderTimeSlice> rentalOrderTimeSlices = rentalOrderTimeSliceService.getRentalOrderTimeSlicesByStartTimeList(totalSubStartTimeStringList);
            List<String> carIds = rentalOrderTimeSlices.stream().map(RentalOrderTimeSlice::getCarId).distinct().collect(Collectors.toList());
            List<CarModel> carModels = CollectionUtils.isEmpty(carIds) ? this.list() : this.getBaseMapper().getCarModelFilteredByCarIds(carIds);
            result = carModels.stream().map(CarModelVo::new).collect(Collectors.toList());
        } catch (ParseException e) {
            throw new BizException(DATE_STRING_FORMAT_ERROR, "due to exist invalid date string, failed to get available car model");
        }
        return result;
    }
}
