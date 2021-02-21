# mian car rental platform

## Overview

- mian car rental platform to provide a platform for customer to rent car.

### Environment

- JDK 1.8.0_192

- Apache Maven 3.5.0

### Characteristic

- Use spring-boot framework with embedded TOMCAT. 

- For simplicity, use H2 as database in in-memory mode.

- Use mybatis-plus.

- Use swagger to test API.

## Functionality

- Provide simple registration and login functions

- Provide customer rental functions

## Database Design

- Refer to `mian-car-rental-platform\src\main\resources\database\schema.sql`

## Key Design

- Provide the process about how to use swagger to simulate users to rent cars `http://localhost:9527/swagger-ui.html` `mian-car-rental-platform\Simulate_User_To_Rent.md` 

- Split the time range into slices, and take advantage of `transaction`/`union unique index` to solve concurrence cases when customers book the same car at the same time range. 

- Current time slice is one hour, the relevant time string used in the system must be on the hour. which means `2021-02-21 17:00` is valid, but `2021-02-21 17:01` is invalid.

- Take advantage of `HandlerInterceptor`/`ResponseBodyAdvice` to realise unity response.

- Take advantage of `ControllerAdvice`/`ExceptionHandler` to realise unity response of exception.

## Relevant URL

- `http://localhost:9527/swagger-ui.html`

- `http://localhost:9527/h2-console`


      

