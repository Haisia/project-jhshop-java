package com.haisia.shop.order.service.domain;

import com.haisia.shop.order.service.domain.entity.Order;
import com.haisia.shop.order.service.domain.entity.OrderInitializer;
import com.haisia.shop.order.service.domain.exception.OrderDomainException;

public class OrderDomainServiceImpl implements OrderDomainService {
  @Override
  public Object validateAndInitiate(Order order) {
    OrderInitializer orderInitializer = new OrderInitializer(order, OrderDomainException::new);
    orderInitializer.validateAndInitialize();
    return null;
  }
}
