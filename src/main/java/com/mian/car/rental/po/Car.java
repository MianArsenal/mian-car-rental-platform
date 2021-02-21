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
@TableName("car")
@NoArgsConstructor
public class Car implements Serializable {

    private static final long serialVersionUID = -1L;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LICENSE_PLATE = "license_plate";
    public static final String COLUMN_CAR_MODEL_ID = "car_model_id";
    public static final String COLUMN_CREATED_AT = "created_at";

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    @TableField(COLUMN_LICENSE_PLATE)
    private String licensePlate;
    @TableField(COLUMN_CAR_MODEL_ID)
    private String carModelId;
    @TableField(COLUMN_CREATED_AT)
    private Date createdAt;

}
