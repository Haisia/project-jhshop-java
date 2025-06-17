package com.haisia.shop.order.service.client.http.webclient.externalpgservice.mapper;

import com.haisia.shop.order.service.client.http.webclient.externalpgservice.dto.CardInfo;
import com.haisia.shop.order.service.domain.payment.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentExternalPGServiceMapper {

  public CardInfo toCardInfo(Payment payment) {
    return CardInfo.builder()
      .cardHolderName(payment.getCardHolderName())
      .cardHolderEmail(payment.getCardHolderEmail())
      .cardNumber(payment.getCardNumber())
      .expiryDate(payment.getExpiryDate())
      .cvc(payment.getCvc())
      .price(payment.getPrice().amount())
      .build();
  }

}
