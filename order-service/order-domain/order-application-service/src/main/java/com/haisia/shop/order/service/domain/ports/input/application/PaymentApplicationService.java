package com.haisia.shop.order.service.domain.ports.input.application;

import com.haisia.shop.common.domain.valueobject.UserSession;
import com.haisia.shop.order.service.domain.dto.create.CreatePaymentCommand;

public interface PaymentApplicationService {
  void create(CreatePaymentCommand command, UserSession userSession);
}
