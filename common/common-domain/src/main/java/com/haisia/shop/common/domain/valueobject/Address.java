package com.haisia.shop.common.domain.valueobject;

import jakarta.persistence.Embeddable;

@Embeddable
public record Address (
  String country,
  String city,
  String street,
  String zipCode,
  String detail
) {
}
