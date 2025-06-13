package com.haisia.shop.order.service.domain.order.event;

import com.haisia.shop.common.domain.event.DomainEvent;
import com.haisia.shop.order.service.domain.order.entity.Order;

import java.time.ZonedDateTime;

public abstract class OrderEvent extends DomainEvent {

  private final Order order;

  protected OrderEvent(Order order, ZonedDateTime createdAt) {
    super(createdAt);
    this.order = order;
  }

  public Order getOrder() {
    return order;
  }
}
