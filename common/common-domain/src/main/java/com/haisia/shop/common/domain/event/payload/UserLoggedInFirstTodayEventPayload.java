package com.haisia.shop.common.domain.event.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserLoggedInFirstTodayEventPayload extends EventPayload {
  private final UUID userAuthId;
  private final LocalDateTime loggedInTime;

  public static final String AGGREGATE_TYPE = "UserLoginRecord";
  public static final String EVENT_NAME = "UserLoggedInFirstToday";

  @JsonCreator
  @Builder
  protected UserLoggedInFirstTodayEventPayload(
    @JsonProperty("sagaId") UUID sagaId,
    @JsonProperty("aggregateId") UUID aggregateId,
    @JsonProperty("userAuthId") UUID userAuthId,
    @JsonProperty("loggedInTime") LocalDateTime loggedInTime
  ) {
    super(sagaId, aggregateId, AGGREGATE_TYPE, EVENT_NAME, null);
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
