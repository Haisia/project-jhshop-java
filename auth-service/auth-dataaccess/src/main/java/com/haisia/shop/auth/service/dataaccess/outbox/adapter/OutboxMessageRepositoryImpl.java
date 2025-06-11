package com.haisia.shop.auth.service.dataaccess.outbox.adapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haisia.shop.auth.service.dataaccess.outbox.entity.OutboxMessageJpaEntity;
import com.haisia.shop.auth.service.dataaccess.outbox.mapper.OutboxMessageDataaccessMapper;
import com.haisia.shop.auth.service.dataaccess.outbox.repository.OutboxMessageJpaRepository;
import com.haisia.shop.auth.service.domain.ports.output.repository.OutboxMessageRepository;
import com.haisia.shop.infrastructure.outbox.OutboxMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OutboxMessageRepositoryImpl implements OutboxMessageRepository {

  private final OutboxMessageJpaRepository repository;
  private final OutboxMessageDataaccessMapper mapper;

  @Override
  public OutboxMessage save(OutboxMessage message) {
    OutboxMessageJpaEntity jpaEntity = mapper.outboxMessageToOutboxMessageJpaEntity(message);
    OutboxMessageJpaEntity save = repository.save(jpaEntity);
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//    mapper.outboxMessageJpaEntityToOutboxMessage(save, jsonNode -> objectMapper.readValue(jsonNode, ))
    return null;
  }
}
