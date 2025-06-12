package com.haisia.shop.catalog.service.domain.mapper;

import com.haisia.shop.catalog.service.domain.dto.create.CreateProductCommand;
import com.haisia.shop.catalog.service.domain.product.entity.Product;
import com.haisia.shop.catalog.service.domain.product.valueobject.ProductInformation;
import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.common.domain.valueobject.Stock;
import com.haisia.shop.common.domain.valueobject.id.SellerId;
import org.springframework.stereotype.Component;

@Component
public class ProductDataMapper {

  public Product createProductCommandToProduct(CreateProductCommand command, SellerId sellerId) {
    return Product.builder()
      .name(command.name())
      .price(new Money(command.price()))
      .stock(new Stock(command.stock()))
      .information(new ProductInformation(command.information().title(), command.information().content()))
      .sellerId(sellerId)
      .build();
  }

}
