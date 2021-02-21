package com.mian.car.rental.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.mian.car.rental.dao")
public class MybatisPlusConfiguration {
}
