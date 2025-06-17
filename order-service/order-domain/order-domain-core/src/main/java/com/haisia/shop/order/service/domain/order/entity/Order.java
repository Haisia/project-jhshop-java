package com.haisia.shop.order.service.domain.order.entity;

import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.common.domain.valueobject.id.OrderId;
import com.haisia.shop.common.domain.valueobject.id.ProductId;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.order.service.domain.order.exception.OrderDomainException;
import com.haisia.shop.order.service.domain.order.valuobject.OrderStatus;
import com.haisia.shop.order.service.domain.order.valuobject.TrackingId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order extends AggregateRoot<OrderId> {
  @AttributeOverride(
    name = "value",
    column = @Column(name = "id", columnDefinition = "uuid", nullable = false)
  )
  @EmbeddedId
  private OrderId id;

  @OrderBy("id DESC")
  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<OrderItem> orderItems = new ArrayList<>();

  @AttributeOverride(
    name = "amount",
    column = @Column(name = "price", nullable = false)
  )
  @Embedded
  private Money price;

  @AttributeOverride(
    name = "value",
    column = @Column(name = "buyer", columnDefinition = "uuid", nullable = false)
  )
  @Embedded
  private UserAuthId buyer;
  @Embedded
  private Address deliveryAddress;
  @AttributeOverride(
    name = "value",
    column = @Column(name = "tracking_id", columnDefinition = "uuid", nullable = false)
  )
  @Embedded
  private TrackingId trackingId;
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;
  private String failureMessage;
  // ---

  @Builder
  private Order(
    List<OrderItem> orderItems,
    Money price,
    UserAuthId buyer,
    Address deliveryAddress,
    TrackingId trackingId,
    OrderStatus orderStatus,
    String failureMessage
  ) {
    this.price = price;
    this.orderItems.addAll(orderItems == null ? Collections.emptyList() : orderItems);
    this.buyer = buyer;
    this.deliveryAddress = deliveryAddress;
    this.trackingId = trackingId;
    this.orderStatus = orderStatus;
    this.failureMessage = failureMessage;
  }

  @Override
  protected void initialize() {
    this.id = new OrderId(UUID.randomUUID());
    this.trackingId = new TrackingId(UUID.randomUUID());
    this.orderStatus = OrderStatus.PENDING;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return Objects.equals(id, order.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  // ---

  public OrderItem addItem(
    ProductId productId,
    int quantity,
    Money price,
    Money subTotal
  ) {
    Optional<OrderItem> findItem = this.orderItems.stream()
      .filter(item -> item.getProductId().equals(productId))
      .findFirst();

    if (findItem.isPresent()) {
      return mergeOrderItem(findItem.get(), quantity, subTotal);
    }

    OrderItem createdOrderItem = OrderItem.builder()
      .order(this)
      .productId(productId)
      .quantity(quantity)
      .price(price)
      .subTotal(subTotal)
      .build();
    this.orderItems.add(createdOrderItem);

    return createdOrderItem;
  }

  private OrderItem mergeOrderItem(
    OrderItem orderItem,
    int quantity,
    Money subTotal
  ) {
    orderItem.addQuantity(quantity);
    orderItem.addSubTotal(subTotal);
    return orderItem;
  }

  public void addFailureMessage(String message) {
    if (message == null) return;
    this.failureMessage = failureMessage == null ? message : String.join(",", this.failureMessage, message, message);
  }

  public void addFailureMessages(List<String> messages) {
    if (messages == null) return;
    addFailureMessage(messages.isEmpty() ? null : String.join(",", messages));
  }

  public void pay() {
    if (orderStatus != OrderStatus.PENDING) {
      throw new OrderDomainException(String.format(
        "orderStatus 가 기대값과 다릅니다. expect: %s, actual: %s",
        OrderStatus.PENDING,
        orderStatus
      ));
    }

    orderStatus = OrderStatus.PAID;
  }

  public void cancel() {
    orderStatus = OrderStatus.CANCELLED;
  }
}
