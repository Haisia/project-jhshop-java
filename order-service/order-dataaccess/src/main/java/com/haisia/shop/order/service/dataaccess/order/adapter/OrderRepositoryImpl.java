package com.haisia.shop.order.service.dataaccess.order.adapter;

import com.haisia.shop.common.domain.valueobject.id.OrderId;
import com.haisia.shop.order.service.dataaccess.order.repository.OrderJpaRepository;
import com.haisia.shop.order.service.domain.order.entity.Order;
import com.haisia.shop.order.service.domain.ports.output.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class OrderRepositoryImpl implements OrderRepository {

  private final OrderJpaRepository repository;

  @Override
  public Order save(Order order) {
    return repository.save(order);
  }

  @Override
  public Optional<Order> findById(OrderId orderId) {
    return repository.findById(orderId);
  }
}
