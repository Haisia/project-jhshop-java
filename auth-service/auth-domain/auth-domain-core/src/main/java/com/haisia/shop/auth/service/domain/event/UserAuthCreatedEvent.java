package com.haisia.shop.auth.service.domain.event;

import com.haisia.shop.auth.service.domain.entity.UserAuth;
import com.haisia.shop.common.domain.event.publisher.DomainEventPublisher;

import java.time.ZonedDateTime;

public class UserAuthCreatedEvent extends UserAuthEvent {

  private final DomainEventPublisher<UserAuthCreatedEvent> eventPublisher;

  public UserAuthCreatedEvent(
    UserAuth userAuth,
    ZonedDateTime createdAt,
    DomainEventPublisher<UserAuthCreatedEvent> eventPublisher
  ) {
    super(userAuth, createdAt);
    this.eventPublisher = eventPublisher;
  }

  @Override
  public void fire() {
    eventPublisher.publish(this);
  }
}
