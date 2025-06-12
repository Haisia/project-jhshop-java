package com.haisia.shop.catalog.service.domain.dto.create;

import java.math.BigDecimal;

public record CreateProductCommand(
  String name,
  BigDecimal price,
  BigDecimal stock,
  ProductInformation information
) {
  public record ProductInformation(
    String title,
    String content
  ) {
  }
}
