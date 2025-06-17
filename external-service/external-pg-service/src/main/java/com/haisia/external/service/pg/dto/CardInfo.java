package com.haisia.external.service.pg.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CardInfo(
  String cardHolderName,
  String cardHolderEmail,
  String cardNumber,
  String expiryDate,
  Integer cvc,
  BigDecimal price
) {
}
