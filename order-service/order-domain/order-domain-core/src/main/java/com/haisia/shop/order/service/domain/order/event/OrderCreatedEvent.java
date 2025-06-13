package com.haisia.shop.order.service.domain.order.event;

import com.haisia.shop.order.service.domain.order.entity.Order;

import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent {
  public OrderCreatedEvent(Order order, ZonedDateTime createdAt) {
    super(order, createdAt);
  }
}
