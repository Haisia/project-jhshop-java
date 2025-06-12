package com.haisia.shop.catalog.service.domain.product.entity;

import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.common.domain.valueobject.id.ProductId;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.catalog.service.domain.product.valueobject.ProductInformation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
  private Money price;
  private BigDecimal stock;
  @Embedded
  private ProductInformation information;

  @AttributeOverride(
    name = "value",
    column = @Column(name = "seller_id", columnDefinition = "uuid", nullable = false)
  )
  @Embedded
  private UserAuthId sellerId;

  @Override
  public ProductId getId() {
    return this.id;
  }
}
