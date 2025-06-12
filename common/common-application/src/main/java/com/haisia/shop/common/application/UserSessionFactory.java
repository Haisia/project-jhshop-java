package com.haisia.shop.common.application;

import com.haisia.shop.common.domain.constants.CustomHttpHeaderConstants;
import com.haisia.shop.common.domain.ports.output.repository.UserSessionRepository;
import com.haisia.shop.common.domain.valueobject.UserSession;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class UserSessionFactory {

  private final UserSessionRepository repository;

  public UserSession getUserSession() {
    HttpServletRequest request =
      ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

    String userAuthId = request.getHeader(CustomHttpHeaderConstants.USER_AUTH_ID);
    if (userAuthId == null) return null;

    return repository.findById(userAuthId).orElse(null);
  }
}
