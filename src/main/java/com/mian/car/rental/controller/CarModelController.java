package com.mian.car.rental.controller;

import com.mian.car.rental.anotation.UnityResponse;
import com.mian.car.rental.service.CarModelService;
import com.mian.car.rental.vo.CarModelVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car-model")
@Api(tags = "car model controller")
@RequiredArgsConstructor
@UnityResponse
public class CarModelController {

    private final CarModelService carModelService;

    @GetMapping("/{startTimeString}/{endTimeString}")
    @ApiOperation(value = "get available car model", notes = "get available car model")
    public List<CarModelVo> getAvailableCarModel(@PathVariable String startTimeString, @PathVariable String endTimeString) {
        return carModelService.getAvailableCarModel(startTimeString, endTimeString);
    }

}
