package com.haisia.shop.infrastructure.outbox;

public interface OutboxScheduler {
    void processOutboxMessage();
}
