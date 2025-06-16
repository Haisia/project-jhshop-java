package com.haisia.shop.common.domain.event.payload;

import com.haisia.shop.common.domain.saga.SagaAction;
import com.haisia.shop.common.domain.saga.SagaStatus;
import lombok.Getter;

import java.util.UUID;

@Getter
public class EventPayload {
  private final UUID sagaId;
  private final UUID aggregateId;
  private final String aggregateType;
  private final String eventName;

  private SagaStatus sagaStatus = SagaStatus.STARTED;
  private SagaAction action;

  public EventPayload(
    UUID sagaId,
    UUID aggregateId,
    String aggregateType,
    String eventName,
    SagaStatus sagaStatus,
    SagaAction action
  ) {
    this.sagaId = sagaId;
    this.aggregateId = aggregateId;
    this.aggregateType = aggregateType;
    this.eventName = eventName;
    if (sagaStatus != null) {
      this.sagaStatus = sagaStatus;
    }
    this.action = action;
  }

  public void setSagaStatus(SagaStatus sagaStatus) {
    this.sagaStatus = sagaStatus;
  }

  public void setAction(SagaAction action) {
    this.action = action;
  }
}
