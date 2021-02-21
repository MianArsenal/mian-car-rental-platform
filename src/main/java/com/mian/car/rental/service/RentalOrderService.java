package com.mian.car.rental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mian.car.rental.po.RentalOrder;
import com.mian.car.rental.vo.RentalOrderVo;

public interface RentalOrderService extends IService<RentalOrder> {

    RentalOrderVo addRentalOrder(RentalOrderVo rentalOrderVo);

}
