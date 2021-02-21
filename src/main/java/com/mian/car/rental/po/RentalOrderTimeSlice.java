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
@TableName("rental_order_time_slice")
@NoArgsConstructor
public class RentalOrderTimeSlice implements Serializable {

    private static final long serialVersionUID = -1L;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_START_TIME_STRING = "start_time_string";
    public static final String COLUMN_RENTAL_ORDER_ID = "rental_order_id";
    public static final String COLUMN_END_TIME_STRING = "end_time_string";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_CAR_ID = "car_id";
    public static final String COLUMN_USER_ID = "user_id";

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    @TableField(COLUMN_RENTAL_ORDER_ID)
    private String rentalOrderId;
    @TableField(COLUMN_START_TIME_STRING)
    private String startTimeString;
    @TableField(COLUMN_END_TIME_STRING)
    private String endTimeString;
    @TableField(COLUMN_CAR_ID)
    private String carId;
    @TableField(COLUMN_USER_ID)
    private String userId;
    @TableField(COLUMN_CREATED_AT)
    private Date createdAt;

}
