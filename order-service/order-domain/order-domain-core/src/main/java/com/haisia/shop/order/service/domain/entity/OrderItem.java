package com.haisia.shop.order.service.domain.entity;

import com.haisia.shop.common.domain.entity.BaseEntity;
import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.common.domain.valueobject.id.ProductId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_item")
@Entity
public class OrderItem extends BaseEntity<Long> {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @JoinColumn(name = "order_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private Order order;

  @AttributeOverride(
    name = "value",
    column = @Column(name = "product_id", columnDefinition = "uuid", nullable = false)
  )
  @Embedded
  private ProductId productId;
  private int quantity;
  @AttributeOverride(
    name = "value",
    column = @Column(name = "price", nullable = false)
  )
  @Embedded
  private Money price;
  @AttributeOverride(
    name = "value",
    column = @Column(name = "sub_total", nullable = false)
  )
  @Embedded
  private Money subTotal;

  // ---

  @Builder(access = AccessLevel.PACKAGE)
  private OrderItem(Long id, Order order, ProductId productId, int quantity, Money price, Money subTotal) {
    this.id = id;
    this.order = order;
    this.productId = productId;
    this.quantity = quantity;
    this.price = price;
    this.subTotal = subTotal;
  }

  @Override
  public Long getId() {
    return id;
  }

  public Order getOrder() {
    return order;
  }

  public ProductId getProductId() {
    return productId;
  }

  public int getQuantity() {
    return quantity;
  }

  public Money getPrice() {
    return price;
  }

  public Money getSubTotal() {
    return subTotal;
  }

  // ---

}
