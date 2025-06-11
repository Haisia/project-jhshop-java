package com.haisia.shop.common.domain.event.payload;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserLoggedInFirstTodayEventPayload extends EventPayload {
  private final UUID userAuthId;
  private final LocalDateTime loggedInTime;

  public static final String AGGREGATE_TYPE = "UserLoginRecord";
  public static final String EVENT_NAME = "UserLoggedInFirstToday";

  @Builder
  private UserLoggedInFirstTodayEventPayload(
    UUID sagaId,
    UUID aggregateId,
    UUID userAuthId,
    LocalDateTime loggedInTime
  ) {
    super(sagaId, aggregateId, AGGREGATE_TYPE, EVENT_NAME);
    this.userAuthId = userAuthId;
    this.loggedInTime = loggedInTime;
  }

  public UUID getUserAuthId() {
    return userAuthId;
  }

  public LocalDateTime getLoggedInTime() {
    return loggedInTime;
  }
}
