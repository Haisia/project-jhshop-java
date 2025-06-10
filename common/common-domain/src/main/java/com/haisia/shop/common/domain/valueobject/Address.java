package com.haisia.shop.common.domain.valueobject;

import lombok.Builder;

@Builder
public record Address (
  String country,
  String city,
  String street,
  String zipCode,
  String detail
) {
}
