package com.mian.car.rental.exception;

import com.mian.car.rental.vo.response.ResponseStatus;
import lombok.Data;

@Data
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -1L;

    private String code;
    private String codeDescription;
    private String message;

    public BizException(ResponseStatus responseStatus, String message) {
        this.code = responseStatus.getCode();
        this.codeDescription = responseStatus.getCodeDescription();
        this.message = message;
    }

}
