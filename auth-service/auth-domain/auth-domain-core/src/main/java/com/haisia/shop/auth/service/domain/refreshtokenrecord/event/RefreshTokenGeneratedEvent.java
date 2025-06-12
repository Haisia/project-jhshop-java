package com.haisia.shop.auth.service.domain.refreshtokenrecord.event;

import com.haisia.shop.common.domain.event.DomainEvent;
import lombok.Builder;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.haisia.shop.common.domain.DomainConstants.UTC;

public class RefreshTokenGeneratedEvent extends DomainEvent {
  private final String refreshToken;

  @Builder
  private RefreshTokenGeneratedEvent(String refreshToken) {
    super(ZonedDateTime.now(ZoneId.of(UTC)));
    this.refreshToken = refreshToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }
}
