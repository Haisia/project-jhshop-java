package com.haisia.shop.order.service.domain.payment.entity;

import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.common.domain.valueobject.id.OrderId;
import com.haisia.shop.common.domain.valueobject.id.PaymentId;
import com.haisia.shop.order.service.domain.payment.valueobject.PaymentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "payment")
@Entity
public class Payment extends AggregateRoot<PaymentId> {
  @AttributeOverride(
    name = "value",
    column = @Column(name = "id", columnDefinition = "uuid", nullable = false)
  )
  @EmbeddedId
  private PaymentId id;

  @AttributeOverride(
    name = "value",
    column = @Column(name = "order_id", columnDefinition = "uuid", nullable = false)
  )
  @Embedded
  private OrderId orderId;
  @AttributeOverride(
    name = "amount",
    column = @Column(name = "price", nullable = false)
  )
  @Embedded
  private Money price;
  @Enumerated(EnumType.STRING)
  private PaymentStatus status;

  // ---

  @Builder
  private Payment(OrderId orderId, Money price, PaymentStatus status) {
    this.orderId = orderId;
    this.price = price;
    this.status = status;
  }

  @Override
  protected void initialize() {
    this.id = new PaymentId(UUID.randomUUID());
    this.status = PaymentStatus.PENDING;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Payment payment = (Payment) o;
    return Objects.equals(id, payment.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  // ---
}
