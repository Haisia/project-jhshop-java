package com.haisia.shop.catalog.service.domain.product.entity;

import com.haisia.shop.catalog.service.domain.product.valueobject.ProductInformation;
import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.common.domain.valueobject.Stock;
import com.haisia.shop.common.domain.valueobject.id.ProductId;
import com.haisia.shop.common.domain.valueobject.id.SellerId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
@Entity
public class Product extends AggregateRoot<ProductId> {

  @AttributeOverride(
    name = "value",
    column = @Column(name = "id", columnDefinition = "uuid", nullable = false)
  )
  @EmbeddedId
  private ProductId id;
  private String name;
  @AttributeOverride(
    name = "amount",
    column = @Column(name = "price", nullable = false)
  )
  @Embedded
  private Money price;
  @AttributeOverride(
    name = "amount",
    column = @Column(name = "stock", nullable = false)
  )
  @Embedded
  private Stock stock;
  @Embedded
  private ProductInformation information;

  @AttributeOverride(
    name = "value",
    column = @Column(name = "seller_id", columnDefinition = "uuid", nullable = false)
  )
  @Embedded
  private SellerId sellerId;

  // ---

  protected void initialize() {
    this.id = new ProductId(UUID.randomUUID());
  }

  @Builder
  private Product(
    String name,
    Money price,
    Stock stock,
    ProductInformation information,
    SellerId sellerId
  ) {
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.information = information;
    this.sellerId = sellerId;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(id, product.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  // ---
}
