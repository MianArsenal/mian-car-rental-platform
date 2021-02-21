package com.mian.car.rental.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mian.car.rental.po.RentalOrderTimeSlice;
import com.mian.car.rental.service.CarModelService;
import com.mian.car.rental.service.RentalOrderTimeSliceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@AutoConfigureMockMvc
@WebMvcTest(CarModelController.class)
class CarModelControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarModelService carModelService;
    @MockBean
    private RentalOrderTimeSliceService rentalOrderTimeSliceService;

    @Test
    void getAvailableCarModel() throws Exception {
        RentalOrderTimeSliceService rentalOrderTimeSliceServiceMock = Mockito.mock(RentalOrderTimeSliceService.class);
        Mockito.when(rentalOrderTimeSliceServiceMock.list(Mockito.any(QueryWrapper.class))).thenReturn(new ArrayList<RentalOrderTimeSlice>());
        mvc.perform(MockMvcRequestBuilders
                .get("/car-model/2021-03-01%2012%3A00/2021-03-02%2015%3A00")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        Mockito.verify(carModelService, Mockito.times(0)).getBaseMapper();
    }
}