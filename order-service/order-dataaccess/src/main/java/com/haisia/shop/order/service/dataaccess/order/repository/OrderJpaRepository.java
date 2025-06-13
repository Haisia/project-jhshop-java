package com.haisia.shop.order.service.dataaccess.order.repository;

import com.haisia.shop.common.domain.valueobject.id.OrderId;
import com.haisia.shop.order.service.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, OrderId> {
}
