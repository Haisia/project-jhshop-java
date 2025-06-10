package com.haisia.shop.common.application.annotation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.haisia.shop.common.domain.constants.CustomHttpHeaderConstants.INTERNAL_ONLY;

@Component
public class InternalOnlyInterceptor implements HandlerInterceptor {

  @Value("${feign.secret.key}")
  private String feignSecretKey;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    if (handler instanceof HandlerMethod handlerMethod) {
      InternalOnly annotation = handlerMethod.getMethodAnnotation(InternalOnly.class);
      if (annotation != null) {
        String headerKey = request.getHeader(INTERNAL_ONLY);
        if (!feignSecretKey.equals(headerKey)) {
          response.setStatus(HttpStatus.FORBIDDEN.value());
          return false;
        }
      }
    }
    return true;
  }
}