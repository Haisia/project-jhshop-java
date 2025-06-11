package com.haisia.shop.auth.service.dataaccess.outbox.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.haisia.shop.auth.service.dataaccess.outbox.entity.OutboxMessageJpaEntity;
import com.haisia.shop.infrastructure.outbox.OutboxMessage;
import org.springframework.stereotype.Component;

@Component
public class OutboxMessageDataaccessMapper {

  public OutboxMessageJpaEntity outboxMessageToOutboxMessageJpaEntity(OutboxMessage outboxMessage) {
    ObjectMapper objectMapper = new ObjectMapper()
      .registerModule(new JavaTimeModule())
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    return OutboxMessageJpaEntity.builder()
      .sagaId(outboxMessage.getSagaId())
      .aggregateId(outboxMessage.getAggregateId())
      .aggregateType(outboxMessage.getAggregateType())
      .eventName(outboxMessage.getEventName())
      .payload(objectMapper.convertValue(outboxMessage.getPayload(), JsonNode.class))
      .build();
  }

}
