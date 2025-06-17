package com.haisia.shop.order.service.domain.mapper;

import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.common.domain.valueobject.id.OrderId;
import com.haisia.shop.order.service.domain.dto.create.CreatePaymentCommand;
import com.haisia.shop.order.service.domain.payment.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataMapper {

  public Payment toPayment(CreatePaymentCommand command) {
    return Payment.builder()
      .orderId(new OrderId(command.orderId()))
      .price(new Money(command.price()))
      .cardHolderName(command.cardHolderName())
      .cardHolderEmail(command.cardHolderEmail())
      .cardNumber(command.cardNumber())
      .expiryDate(command.expiryDate())
      .cvc(command.cvc())
      .build();
  }

}
