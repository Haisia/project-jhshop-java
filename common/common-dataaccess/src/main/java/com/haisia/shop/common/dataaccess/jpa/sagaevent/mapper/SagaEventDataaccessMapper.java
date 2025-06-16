package com.haisia.shop.common.dataaccess.jpa.sagaevent.mapper;

import com.haisia.shop.common.domain.event.payload.EventPayload;
import com.haisia.shop.common.dataaccess.jpa.sagaevent.entity.SagaEventJpaEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SagaEventDataaccessMapper {

  public SagaEventJpaEntity toJpaEntity(EventPayload payload) {
    return SagaEventJpaEntity.builder()
      .id(payload.getSagaId())
      .aggregateId(payload.getAggregateId())
      .aggregateType(payload.getAggregateType())
      .eventName(payload.getEventName())
      .status(payload.getSagaStatus())
      .action(payload.getAction())
      .failureMessages(payload.getFailureMessages().isEmpty() ? null : String.join(",", payload.getFailureMessages()))
      .build();
  }

  public EventPayload toEventPayload(SagaEventJpaEntity jpaEntity) {
    return new EventPayload(
      jpaEntity.getId(),
      jpaEntity.getAggregateId(),
      jpaEntity.getAggregateType(),
      jpaEntity.getEventName(),
      jpaEntity.getStatus(),
      jpaEntity.getAction(),
      jpaEntity.getFailureMessages()
    );
  }
}
