package com.haisia.shop.common.application.config;

import com.haisia.shop.common.application.UserSessionFactory;
import com.haisia.shop.common.domain.valueobject.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

  private final UserSessionFactory userSessionFactory;

  @Override
  public Optional<String> getCurrentAuditor() {
    UserSession userSession = null;
    try {
      userSession = userSessionFactory.getUserSession();
    } catch(Exception e) {
      return Optional.of("SYSTEM");
    }

    if (userSession != null && userSession.userAuthId() != null) {
      return Optional.of(userSession.userAuthId());
    }

    return Optional.of("SYSTEM");
  }
}
