package com.haisia.shop.common.domain.event.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haisia.shop.common.domain.saga.SagaAction;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class UserLoggedInFirstTodayEventPayload extends EventPayload {
  private final UUID userAuthId;
  private final LocalDateTime loggedInTime;

  public static final String AGGREGATE_TYPE = "UserLoginRecord";
  public static final String EVENT_NAME = "UserLoggedInFirstToday";

  @JsonCreator
  @Builder
  protected UserLoggedInFirstTodayEventPayload(
    UUID sagaId,
    UUID aggregateId,
    UUID userAuthId,
    LocalDateTime loggedInTime,
    SagaAction sagaAction
  ) {
    super(sagaId, aggregateId, AGGREGATE_TYPE, EVENT_NAME, null, sagaAction, null);
    this.userAuthId = userAuthId;
    this.loggedInTime = loggedInTime;
  }
}
