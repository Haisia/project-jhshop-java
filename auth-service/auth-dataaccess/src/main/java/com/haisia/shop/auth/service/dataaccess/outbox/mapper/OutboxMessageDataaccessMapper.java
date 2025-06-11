package com.haisia.shop.auth.service.dataaccess.outbox.mapper;

import com.haisia.shop.auth.service.dataaccess.outbox.entity.OutboxMessageJpaEntity;
import com.haisia.shop.common.domain.event.OutboxMessage;
import org.springframework.stereotype.Component;

@Component
public class OutboxMessageDataaccessMapper {

  public OutboxMessageJpaEntity outboxMessageToOutboxMessageJpaEntity(OutboxMessage outboxMessage) {
    return OutboxMessageJpaEntity.builder()
      .sagaId(outboxMessage.getSagaId())
      .aggregateId(outboxMessage.getAggregateId())
      .aggregateType(outboxMessage.getAggregateType())
      .eventName(outboxMessage.getEventName())
      .payload(outboxMessage.getPayload().toJsonNode())
      .build();
  }

  public OutboxMessage outboxMessageJpaEntityToOutboxMessage(
    OutboxMessageJpaEntity outboxMessageJpaEntity,
    OutboxPayloadConvertor outboxPayloadConvertor
  ) {
    return OutboxMessage.builder()
      .sagaId(outboxMessageJpaEntity.getSagaId())
      .aggregateId(outboxMessageJpaEntity.getAggregateId())
      .aggregateType(outboxMessageJpaEntity.getAggregateType())
      .eventName(outboxMessageJpaEntity.getEventName())
      .createdAt(outboxMessageJpaEntity.getCreatedAt())
      .payload(outboxPayloadConvertor.apply(outboxMessageJpaEntity.getPayload()))
      .build();
  }

}
