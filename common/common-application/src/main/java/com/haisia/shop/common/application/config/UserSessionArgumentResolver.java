package com.haisia.shop.common.application.config;

import com.haisia.shop.common.application.UserSessionFactory;
import com.haisia.shop.common.application.annotation.InjectUserSession;
import com.haisia.shop.common.application.exception.ApplicationException;
import com.haisia.shop.common.domain.valueobject.UserSession;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserSessionArgumentResolver implements HandlerMethodArgumentResolver {

  private final UserSessionFactory userSessionFactory;

  public UserSessionArgumentResolver(UserSessionFactory userSessionFactory) {
    this.userSessionFactory = userSessionFactory;
  }

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(InjectUserSession.class) &&
      UserSession.class.isAssignableFrom(parameter.getParameterType());
  }

  @Override
  public Object resolveArgument(
    MethodParameter parameter,
    ModelAndViewContainer mavContainer,
    NativeWebRequest webRequest,
    WebDataBinderFactory binderFactory
  ) {
    UserSession userSession = userSessionFactory.getUserSession();
    if (userSession == null) {
      throw new ApplicationException("로그인이 필요합니다.");
    }
    return userSession;
  }
}