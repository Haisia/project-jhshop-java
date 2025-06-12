package com.haisia.shop.catalog.service.domain.product.entity;

import com.haisia.shop.catalog.service.domain.product.exception.ProductDomainException;
import com.haisia.shop.common.domain.entity.AggregateRoot;
import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.common.domain.valueobject.Stock;
import com.haisia.shop.common.domain.valueobject.id.ProductId;
import com.haisia.shop.common.domain.valueobject.id.SellerId;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.catalog.service.domain.product.valueobject.ProductInformation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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

  @Builder
  private Product(
    ProductId id,
    String name,
    Money price,
    Stock stock,
    ProductInformation information,
    SellerId sellerId
  ) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.information = information;
    this.sellerId = sellerId;
  }

  @Override
  public ProductId getId() {
    return this.id;
  }

  public void validate() {
    if (!validateName()
      || !validatePrice()
      || !validateStock()
      || !validateInformation()
      || !validateSellerId()
    ) throw new ProductDomainException("Product 의 검증에 실패하였습니다. " +
      "name: " + name + ", price: " + price + ", stock: " + stock
      + ", information: " + information + ", sellerId: " + sellerId);
  }

  public void initialize() {
    this.id = new ProductId(UUID.randomUUID());
  }

  private boolean validateName() {
    return name != null && name.length() > 1;
  }

  private boolean validatePrice() {
    return price != null && price.isGreaterThanZero();
  }

  private boolean validateStock() {
    return stock != null && stock.isNotNegative();
  }

  private boolean validateInformation() {
    return information != null
      && information.title() != null && information.title().length() > 1
      && information.content() != null && information.content().length() > 1;
  }

  private boolean validateSellerId() {
    return sellerId != null && sellerId.getValue() != null;
  }

  public void setId(ProductId id) {
    this.id = id;
  }
}
