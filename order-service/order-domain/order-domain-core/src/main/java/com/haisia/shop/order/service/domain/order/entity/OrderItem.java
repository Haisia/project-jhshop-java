package com.haisia.shop.order.service.domain.order.entity;

import com.haisia.shop.common.domain.entity.BaseEntity;
import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.common.domain.valueobject.id.ProductId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_item")
@Entity
public class OrderItem extends BaseEntity<Long> {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @JoinColumn(name = "order_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Order order;

  @AttributeOverride(
    name = "value",
    column = @Column(name = "product_id", columnDefinition = "uuid", nullable = false)
  )
  @Embedded
  private ProductId productId;
  private int quantity;
  @AttributeOverride(
    name = "amount",
    column = @Column(name = "price", nullable = false)
  )
  @Embedded
  private Money price;
  @AttributeOverride(
    name = "amount",
    column = @Column(name = "sub_total", nullable = false)
  )
  @Embedded
  private Money subTotal;

  // ---

  @Builder(access = AccessLevel.PROTECTED)
  private OrderItem(Order order, ProductId productId, int quantity, Money price, Money subTotal) {
    this.order = order;
    this.productId = productId;
    this.quantity = quantity;
    this.price = price;
    this.subTotal = subTotal;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    OrderItem orderItem = (OrderItem) o;
    return Objects.equals(id, orderItem.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  // ---

  protected void addQuantity(int quantity) {
    this.quantity += quantity;
  }

  protected void addSubTotal(Money subTotal) {
    this.subTotal = this.subTotal.add(subTotal);
  }

}
