package com.mian.car.rental.vo.response;

import com.mian.car.rental.exception.BizException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {

    private static final long serialVersionUID = -1L;

    @ApiModelProperty("Response Code: 2XXX-Success, 3XXX-Redirect, 4XXX/5XXX-Error")
    private String code;
    @ApiModelProperty("Response Code Description")
    private String codeDescription;
    @ApiModelProperty("Timestamp")
    private Long timestamp = System.currentTimeMillis();
    @ApiModelProperty("Response Data")
    private Object data;

    public Response(Object data) {
        this(ResponseStatus.SUCCESS, data);
    }

    public Response(ResponseStatus responseStatus, Object data) {
        this.code = responseStatus.code;
        this.codeDescription = responseStatus.codeDescription;
        this.data = data;
    }

    public Response(BizException bizException) {
        this.code = bizException.getCode();
        this.codeDescription = bizException.getCodeDescription();
        this.data = bizException.getMessage();
    }
}
