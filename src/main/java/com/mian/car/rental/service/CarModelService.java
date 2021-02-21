package com.mian.car.rental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mian.car.rental.po.CarModel;
import com.mian.car.rental.vo.CarModelVo;

import java.util.List;

public interface CarModelService extends IService<CarModel> {
    List<CarModelVo> getAvailableCarModel(String startTimeString, String endTimeString);
}
