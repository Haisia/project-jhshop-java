package com.haisia.shop.auth.service.domain.ports.output.repository;

import com.haisia.shop.auth.service.domain.outbox.OutboxMessage;

import java.util.Optional;

public interface OutboxRepository {
  Optional<OutboxMessage> save(OutboxMessage outboxMessage);
}
