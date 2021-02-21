package com.mian.car.rental.configuration.handler;

import com.mian.car.rental.exception.BizException;
import com.mian.car.rental.vo.response.Response;
import com.mian.car.rental.vo.response.ResponseStatus;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@ControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Response handleBizException(BizException e) {
        return new Response(e);
    }

    @ExceptionHandler(value = {DataAccessException.class, SQLException.class})
    @ResponseBody
    public Response handleDataAccessException() {
        return new Response(ResponseStatus.DATABASE_FAIL, null);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String handleOtherException(Exception e) {
        return e.getMessage();
    }

}
