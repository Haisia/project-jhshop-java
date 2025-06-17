package com.haisia.shop.order.service.client.http.webclient.externalpgservice.dto;

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
