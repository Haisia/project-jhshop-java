package com.haisia.shop.auth.service.domain.ports.output.repository;

import com.haisia.shop.common.domain.event.OutboxMessage;

public interface OutboxMessageRepository {

  OutboxMessage save(OutboxMessage message);

}
