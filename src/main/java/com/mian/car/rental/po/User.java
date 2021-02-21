package com.mian.car.rental.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mian.car.rental.vo.UserVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user")
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_CREATED_AT = "created_at";

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    @TableField(COLUMN_NAME)
    private String name;
    @TableField(COLUMN_PASSWORD)
    private String password;
    @TableField(COLUMN_PHONE)
    private String phone;
    @TableField(COLUMN_CREATED_AT)
    private Date createdAt;

    public User(UserVo userVo) {
        BeanUtils.copyProperties(userVo, this);
    }
}

