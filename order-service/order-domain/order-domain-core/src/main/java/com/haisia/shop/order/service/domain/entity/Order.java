package com.haisia.shop.order.service.domain.entity;

import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.id.OrderId;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.order.service.domain.veluobject.OrderStatus;
import com.haisia.shop.order.service.domain.veluobject.TrackingId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
  private List<OrderItem> orderItems = new ArrayList<>();

  @AttributeOverride(
    name = "value",
    column = @Column(name = "buyer", columnDefinition = "uuid", nullable = false)
  )
  @Embedded
  private UserAuthId buyer;
  @AttributeOverride(
    name = "value",
    column = @Column(name = "seller", columnDefinition = "uuid", nullable = false)
  )
  @Embedded
  private UserAuthId seller;
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

  // ---

  @Builder
  private Order(
    List<OrderItem> orderItems,
    UserAuthId buyer,
    UserAuthId seller,
    Address deliveryAddress,
    TrackingId trackingId,
    OrderStatus orderStatus
  ) {
    this.orderItems = orderItems;
    this.buyer = buyer;
    this.seller = seller;
    this.deliveryAddress = deliveryAddress;
    this.trackingId = trackingId;
    this.orderStatus = orderStatus;
  }

  @Override
  protected void initialize() {
    this.id = new OrderId(UUID.randomUUID());
  }

  @Override
  public OrderId getId() {
    return id;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public UserAuthId getBuyer() {
    return buyer;
  }

  public UserAuthId getSeller() {
    return seller;
  }

  public Address getDeliveryAddress() {
    return deliveryAddress;
  }

  public TrackingId getTrackingId() {
    return trackingId;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  // ---
}
