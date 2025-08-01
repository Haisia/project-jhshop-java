package com.haisia.shop.order.service.domain.order;

import com.haisia.shop.common.domain.utile.CustomTimeFactory;
import com.haisia.shop.order.service.domain.order.entity.Order;
import com.haisia.shop.order.service.domain.order.entity.OrderInitializer;
import com.haisia.shop.order.service.domain.order.event.OrderCreatedEvent;
import com.haisia.shop.order.service.domain.order.exception.OrderDomainException;
import com.haisia.shop.order.service.domain.order.valuobject.OrderStatus;

public class OrderDomainServiceImpl implements OrderDomainService {

  @Override
  public OrderCreatedEvent validateAndInitiate(Order order) {
    OrderInitializer orderInitializer = new OrderInitializer(order, OrderDomainException::new);
    orderInitializer.validateAndInitialize();

    return new OrderCreatedEvent(order, CustomTimeFactory.now());
  }

  @Override
  public void pay(Order order) {
    order.pay();
  }

  @Override
  public void cancel(Order order) {
    order.cancel();
  }
}
