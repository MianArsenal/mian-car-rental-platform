package com.mian.car.rental.controller;

import com.mian.car.rental.anotation.UnityResponse;
import com.mian.car.rental.po.User;
import com.mian.car.rental.service.UserService;
import com.mian.car.rental.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "user controller")
@RequiredArgsConstructor
@UnityResponse
public class UserController {

    private static final String HANDLE_SUCCESS = "SUCCESS";
    private static final String HANDLE_FAIL = "FAIL";

    private final UserService userService;

    @PostMapping
    @ApiOperation(value = "add user", notes = "add a new user")
    public String addUser(@RequestBody UserVo userVo) {
        return userService.save(new User(userVo)) ? HANDLE_SUCCESS : HANDLE_FAIL;
    }

    @PostMapping("/login")
    @ApiOperation(value = "login user", notes = "login user")
    public String loginUser(@RequestBody UserVo userVo) {
        return userService.findUser(userVo.getName(), userVo.getPassword()) != null ? HANDLE_SUCCESS : HANDLE_FAIL;
    }
}
