package com.haisia.shop.common.domain.event.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
public class OrderCreatedEventPayload extends EventPayload {
  private final List<OrderItem> orderItems;
  private final BigDecimal price;
  private final UUID buyerUserAuthId;
  private final UUID sellerUserAuthId;

  @Builder
  public record OrderItem(
    UUID productId,
    int quantity,
    BigDecimal price,
    BigDecimal subTotal
  ) {
  }

  public static final String AGGREGATE_TYPE = "Order";
  public static final String EVENT_NAME = "OrderCreated";

  @Builder
  @JsonCreator
  public OrderCreatedEventPayload(
    UUID sagaId,
    UUID aggregateId,
    List<OrderItem> orderItems,
    BigDecimal price,
    UUID buyerUserAuthId,
    UUID sellerUserAuthId
  ) {
    super(sagaId, aggregateId, AGGREGATE_TYPE, EVENT_NAME, null);
    this.orderItems = orderItems;
    this.price = price;
    this.buyerUserAuthId = buyerUserAuthId;
    this.sellerUserAuthId = sellerUserAuthId;
  }
}
