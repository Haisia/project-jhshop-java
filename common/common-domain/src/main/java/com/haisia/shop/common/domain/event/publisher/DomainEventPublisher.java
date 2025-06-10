package com.haisia.shop.common.domain.event.publisher;

import com.haisia.shop.common.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

  void publish(T domainEvent);

}
