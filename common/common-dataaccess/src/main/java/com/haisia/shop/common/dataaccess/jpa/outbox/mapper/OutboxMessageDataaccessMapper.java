package com.haisia.shop.common.dataaccess.jpa.outbox.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haisia.shop.common.dataaccess.jpa.outbox.entity.OutboxMessageJpaEntity;
import com.haisia.shop.common.domain.outbox.OutboxMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OutboxMessageDataaccessMapper {

  private final ObjectMapper objectMapper;

  public OutboxMessageJpaEntity toJpaEntity(OutboxMessage outboxMessage) {
    return OutboxMessageJpaEntity.builder()
      .sagaId(outboxMessage.getSagaId())
      .aggregateId(outboxMessage.getAggregateId())
      .aggregateType(outboxMessage.getAggregateType())
      .eventName(outboxMessage.getEventName())
      .action(outboxMessage.getAction())
      .payload(objectMapper.convertValue(outboxMessage.getPayload(), JsonNode.class))
      .build();
  }

}
