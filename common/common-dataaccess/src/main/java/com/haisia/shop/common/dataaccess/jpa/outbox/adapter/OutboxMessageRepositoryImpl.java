package com.haisia.shop.common.dataaccess.jpa.outbox.adapter;

import com.haisia.shop.common.dataaccess.jpa.outbox.entity.OutboxMessageJpaEntity;
import com.haisia.shop.common.dataaccess.jpa.outbox.mapper.OutboxMessageDataaccessMapper;
import com.haisia.shop.common.dataaccess.jpa.outbox.repository.OutboxMessageJpaRepository;
import com.haisia.shop.common.domain.outbox.OutboxMessage;
import com.haisia.shop.common.domain.ports.output.repository.OutboxMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OutboxMessageRepositoryImpl implements OutboxMessageRepository {

  private final OutboxMessageJpaRepository repository;
  private final OutboxMessageDataaccessMapper mapper;

  @Override
  public OutboxMessage save(OutboxMessage message) {
    OutboxMessageJpaEntity jpaEntity = mapper.toJpaEntity(message);
    OutboxMessageJpaEntity save = repository.save(jpaEntity);
//    mapper.outboxMessageJpaEntityToOutboxMessage(save, jsonNode -> objectMapper.readValue(jsonNode, ))
    return null;
  }
}
