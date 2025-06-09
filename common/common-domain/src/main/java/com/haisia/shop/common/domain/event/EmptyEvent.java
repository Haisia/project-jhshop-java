package com.haisia.shop.common.domain.event;

public final class EmptyEvent implements DomainEvent<Void> {

  public static final EmptyEvent INSTANCE = new EmptyEvent();

  public EmptyEvent() {
  }

  @Override
  public void fire() {

  }
}
