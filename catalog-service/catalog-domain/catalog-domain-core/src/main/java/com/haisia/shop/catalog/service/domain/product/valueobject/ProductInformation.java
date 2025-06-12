package com.haisia.shop.catalog.service.domain.product.valueobject;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProductInformation(
  String title,
  String content
) {
}
