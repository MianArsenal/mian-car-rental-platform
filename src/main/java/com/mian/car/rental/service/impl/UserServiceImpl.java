package com.mian.car.rental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mian.car.rental.dao.UserMapper;
import com.mian.car.rental.po.User;
import com.mian.car.rental.service.UserService;
import com.mian.car.rental.vo.UserVo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User findUser(String userName, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(User.COLUMN_NAME, userName)
                .eq(User.COLUMN_PASSWORD, password);
        return this.getOne(wrapper);
    }

}
