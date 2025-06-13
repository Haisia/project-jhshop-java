package com.haisia.shop.order.service.domain.ports.output.repository;

import com.haisia.shop.common.domain.valueobject.id.OrderId;
import com.haisia.shop.order.service.domain.order.entity.Order;

import java.util.Optional;

public interface OrderRepository {
  Order save(Order order);
  Optional<Order> findById(OrderId orderId);
}
