package com.mian.car.rental.configuration.handler;

import com.google.gson.Gson;
import com.mian.car.rental.interceptor.UnityResponseInterceptor;
import com.mian.car.rental.vo.response.Response;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@ControllerAdvice
public class UnityResponseBodyAdviceHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return false;
        }
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        Object attribute = servletRequestAttributes.getRequest().getAttribute(UnityResponseInterceptor.IF_UNITY_RESPONSE);
        return Objects.nonNull(attribute) && (boolean) attribute;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof Response) {
            return o;
        }
        Response response = new Response(o);
        if (o instanceof String) {
            return new Gson().toJson(response);
        }
        return response;
    }
}
