package com.mian.car.rental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mian.car.rental.po.User;
import com.mian.car.rental.vo.UserVo;

public interface UserService extends IService<User> {

    User findUser(String userName, String password);
}
