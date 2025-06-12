package com.haisia.shop.common.domain.valueobject;

import jakarta.persistence.Embeddable;

@Embeddable
public record PhoneNumber(
  String number
) {
}
