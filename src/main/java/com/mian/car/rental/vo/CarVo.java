package com.mian.car.rental.vo;

import com.mian.car.rental.po.Car;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(value = "CarVo", description = "car value object")
public class CarVo implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "license plate")
    private String licensePlate;
    @ApiModelProperty(value = "car model id")
    private String carModelId;
    @ApiModelProperty(value = "created date")
    private Date createdAt;

    public CarVo(Car car) {
        BeanUtils.copyProperties(car, this);
    }
}
