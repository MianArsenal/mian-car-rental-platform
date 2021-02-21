package com.mian.car.rental.controller;

import com.mian.car.rental.anotation.UnityResponse;
import com.mian.car.rental.service.RentalOrderService;
import com.mian.car.rental.vo.RentalOrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rental-order")
@Api(tags = "rental order controller")
@RequiredArgsConstructor
@UnityResponse
public class RentalOrderController {

    private final RentalOrderService rentalOrderService;

    @PostMapping
    @ApiOperation(value = "add rental order", notes = "add a new rental order")
    public RentalOrderVo addRentalOrder(@RequestBody RentalOrderVo rentalOrderVo) {
        return rentalOrderService.addRentalOrder(rentalOrderVo);
    }

}
