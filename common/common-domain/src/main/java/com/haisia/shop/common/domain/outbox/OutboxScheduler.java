package com.haisia.shop.common.domain.outbox;

public interface OutboxScheduler {
    void processOutboxMessage();
}
