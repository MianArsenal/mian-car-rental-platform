package com.mian.car.rental.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("car_model")
@NoArgsConstructor
public class CarModel implements Serializable {

    private static final long serialVersionUID = -1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    @TableField("name")
    private String name;
    @TableField("price_per_hour")
    private Integer pricePerHour;
    @TableField("in_stock")
    private Integer inStock;
    @TableField("created_at")
    private Date createdAt;

}
