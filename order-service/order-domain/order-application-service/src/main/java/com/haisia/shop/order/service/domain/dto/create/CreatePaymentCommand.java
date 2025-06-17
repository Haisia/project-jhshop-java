package com.haisia.shop.order.service.domain.dto.create;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatePaymentCommand(
  UUID orderId,
  BigDecimal price,
  String cardHolderName,
  String cardHolderEmail,
  String cardNumber,
  String expiryDate,
  Integer cvc
) {
}
