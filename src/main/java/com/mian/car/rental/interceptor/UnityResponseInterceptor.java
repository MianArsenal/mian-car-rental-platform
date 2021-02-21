package com.mian.car.rental.interceptor;

import com.mian.car.rental.anotation.UnityResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class UnityResponseInterceptor implements HandlerInterceptor {

    public static final String IF_UNITY_RESPONSE = "ifUnityResponse";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        request.setAttribute(IF_UNITY_RESPONSE, ifNeedUnityResponse(handlerMethod));
        return true;
    }

    private boolean ifNeedUnityResponse(HandlerMethod handlerMethod) {
        Class<?> beanType = handlerMethod.getBeanType();
        Method method = handlerMethod.getMethod();
        if (beanType.isAnnotationPresent(UnityResponse.class)) {
            UnityResponse beanTypeAnnotation = beanType.getAnnotation(UnityResponse.class);
            if (beanTypeAnnotation.required()) {
                return ifNeedUnityResponseMethod(method, true);
            } else{
                return false;
            }
        } else {
            return ifNeedUnityResponseMethod(method, false);
        }
    }

    private boolean ifNeedUnityResponseMethod(Method method, boolean defaultValue) {
        if (method.isAnnotationPresent(UnityResponse.class)) {
            return method.getAnnotation(UnityResponse.class).required();
        }
        return defaultValue;
    }
}
