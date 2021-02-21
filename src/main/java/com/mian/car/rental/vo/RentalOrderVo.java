package com.mian.car.rental.vo;

import com.mian.car.rental.po.RentalOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(value = "RentalOrderVo", description = "rental order value object")
public class RentalOrderVo implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "car id")
    private String carId;
    @ApiModelProperty(value = "user id")
    private String userId;
    @ApiModelProperty(value = "start time string")
    private String startTimeString;
    @ApiModelProperty(value = "end time string")
    private String endTimeString;
    @ApiModelProperty(value = "total cost")
    private Integer totalCost;
    @ApiModelProperty(value = "created date")
    private Date createdAt;
    @ApiModelProperty(value = "car model id")
    private String carModelId;
    @ApiModelProperty(value = "car information")
    private CarVo car;

    public RentalOrderVo(RentalOrder rentalOrder) {
        BeanUtils.copyProperties(rentalOrder, this);
    }
}
