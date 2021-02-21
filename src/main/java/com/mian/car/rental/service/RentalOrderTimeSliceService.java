package com.mian.car.rental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mian.car.rental.po.RentalOrderTimeSlice;

import java.util.List;

public interface RentalOrderTimeSliceService extends IService<RentalOrderTimeSlice> {

    List<RentalOrderTimeSlice> getRentalOrderTimeSlicesByStartTimeList(List<String> startTimeList);

}
