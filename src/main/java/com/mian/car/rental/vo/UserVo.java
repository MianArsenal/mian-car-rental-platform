package com.mian.car.rental.vo;

import com.mian.car.rental.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(value = "UserVo", description = "user value object")
public class UserVo implements Serializable {

    private static final long serialVersionUID = -1L;

    public UserVo(User user) {
        BeanUtils.copyProperties(user, this);
    }

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "name")
    private String name;
    @ApiModelProperty(value = "password")
    private String password;
    @ApiModelProperty(value = "phone number")
    private String phone;
    @ApiModelProperty(value = "create date")
    private Date createdAt;

}
