package com.mian.car.rental.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mian.car.rental.vo.RentalOrderVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("rental_order")
@NoArgsConstructor
public class RentalOrder implements Serializable {

    private static final long serialVersionUID = -1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    @TableField("car_id")
    private String carId;
    @TableField("user_id")
    private String userId;
    @TableField("start_time_string")
    private String startTimeString;
    @TableField("end_time_string")
    private String endTimeString;
    @TableField("total_cost")
    private Integer totalCost;
    @TableField("created_at")
    private Date createdAt;

    public RentalOrder(RentalOrderVo rentalOrderVo) {
        BeanUtils.copyProperties(rentalOrderVo, this);
    }

}
