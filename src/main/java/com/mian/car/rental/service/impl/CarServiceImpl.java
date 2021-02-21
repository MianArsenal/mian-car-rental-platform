package com.mian.car.rental.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mian.car.rental.dao.CarMapper;
import com.mian.car.rental.po.Car;
import com.mian.car.rental.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

}
