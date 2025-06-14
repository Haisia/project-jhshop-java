package com.haisia.shop.order.service.domain.dto.create;

import com.haisia.shop.common.domain.valueobject.Address;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateOrderCommand(
  List<Item> orderItems,
  BigDecimal price,
  Address deliveryAddress
) {
  public record Item(
    UUID productId,
    Integer quantity,
    BigDecimal price,
    BigDecimal subTotal
  ){}
}
