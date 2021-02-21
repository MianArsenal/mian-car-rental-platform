package com.mian.car.rental.controller;

import com.google.gson.Gson;
import com.mian.car.rental.service.RentalOrderService;
import com.mian.car.rental.vo.RentalOrderVo;
import org.junit.jupiter.api.Test;
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
@WebMvcTest(RentalOrderController.class)
class RentalOrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RentalOrderService rentalOrderService;

    @Test
    void addRentalOrder() throws Exception {
        RentalOrderVo rentalOrderVo = new RentalOrderVo() {{
            setCarModelId("1");
            setStartTimeString("2021-03-02 17:00");
            setEndTimeString("2021-03-02 15:00");
            setUserId("1");

        }};
        mvc.perform(MockMvcRequestBuilders
                .post("/rental-order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(rentalOrderVo)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}