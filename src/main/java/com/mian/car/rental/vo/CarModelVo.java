package com.mian.car.rental.vo;

import com.mian.car.rental.po.CarModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(value = "CarModelVo", description = "car model value object")
public class CarModelVo  implements Serializable {

    private static final long serialVersionUID = -1L;

    public CarModelVo(CarModel carModel) {
        BeanUtils.copyProperties(carModel, this);
    }

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "name")
    private String name;
    @ApiModelProperty(value = "price per hour")
    private Integer pricePerHour;
    @ApiModelProperty(value = "in stock")
    private Integer inStock;
    @ApiModelProperty(value = "created date")
    private Date createdAt;

}
