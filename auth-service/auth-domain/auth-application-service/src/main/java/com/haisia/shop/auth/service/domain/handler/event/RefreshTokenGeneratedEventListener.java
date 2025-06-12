package com.haisia.shop.auth.service.domain.handler.event;

import com.haisia.shop.auth.service.domain.config.JwtTokenProvider;
import com.haisia.shop.auth.service.domain.refreshtokenrecord.event.RefreshTokenGeneratedEvent;
import com.haisia.shop.common.domain.ports.output.repository.UserSessionRepository;
import com.haisia.shop.common.domain.valueobject.UserSession;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Component
public class RefreshTokenGeneratedEventListener {

  @Value("${jwt.refresh-token.expiration-ms}")
  private Long refreshTokenExpirationMs;

  private final UserSessionRepository userSessionRepository;
  private final JwtTokenProvider refreshTokenProvider;

  @Async
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void handleRefreshTokenGeneratedEvent(RefreshTokenGeneratedEvent event) {
    Long expiration = refreshTokenExpirationMs / 1000;

    String email = refreshTokenProvider.getEmail(event.getRefreshToken());
    UserAuthId userAuthId = refreshTokenProvider.getUserAuthId(event.getRefreshToken());

    UserSession userSession = UserSession.builder()
      .userAuthId(userAuthId.getValue().toString())
      .email(email)
      .expiration(expiration)
      .build();

    userSessionRepository.save(userSession);
  }
}
