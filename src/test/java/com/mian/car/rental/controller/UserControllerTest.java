package com.mian.car.rental.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.mian.car.rental.po.User;
import com.mian.car.rental.service.UserService;
import com.mian.car.rental.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    void addUser() throws Exception {
        UserVo userVo = new UserVo(){{
            setName("MiAn001");
            setPassword("Pass");
            setPhone("13322223333");
        }};
        BDDMockito.given(userService.save(new User(userVo))).willReturn(true);
        mvc.perform(MockMvcRequestBuilders
                .post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(userVo)))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void loginUser() throws Exception {
        UserVo userVo = new UserVo(){{
            setName("MiAn001");
            setPassword("Pass");
        }};
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(User.COLUMN_NAME, userVo.getName())
                .eq(User.COLUMN_PASSWORD, userVo.getPassword());
        BDDMockito.given(userService.getOne(queryWrapper)).willReturn(new User(userVo));
        mvc.perform(MockMvcRequestBuilders
                .post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(userVo)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}