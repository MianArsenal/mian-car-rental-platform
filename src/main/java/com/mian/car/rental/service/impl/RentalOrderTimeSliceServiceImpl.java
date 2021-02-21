package com.mian.car.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mian.car.rental.dao.RentalOrderTimeSliceMapper;
import com.mian.car.rental.po.RentalOrderTimeSlice;
import com.mian.car.rental.service.RentalOrderTimeSliceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalOrderTimeSliceServiceImpl extends ServiceImpl<RentalOrderTimeSliceMapper, RentalOrderTimeSlice> implements RentalOrderTimeSliceService {

    @Override
    public List<RentalOrderTimeSlice> getRentalOrderTimeSlicesByStartTimeList(List<String> startTimeList) {
        QueryWrapper<RentalOrderTimeSlice> wrapper = new QueryWrapper<>();
        wrapper.in(RentalOrderTimeSlice.COLUMN_START_TIME_STRING, startTimeList);
        return this.list(wrapper);
    }

}
