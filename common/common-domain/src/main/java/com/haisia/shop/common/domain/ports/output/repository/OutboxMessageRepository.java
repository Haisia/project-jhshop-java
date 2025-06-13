package com.haisia.shop.common.domain.ports.output.repository;

import com.haisia.shop.common.domain.outbox.OutboxMessage;

public interface OutboxMessageRepository {

  OutboxMessage save(OutboxMessage message);

}
