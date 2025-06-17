package com.haisia.shop.order.service.application.rest;

import com.haisia.shop.common.application.annotation.InjectUserSession;
import com.haisia.shop.common.domain.valueobject.UserSession;
import com.haisia.shop.order.service.domain.dto.create.CreatePaymentCommand;
import com.haisia.shop.order.service.domain.ports.input.application.PaymentApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {

  private final PaymentApplicationService service;

  @PostMapping
  public void create(@RequestBody CreatePaymentCommand command, @InjectUserSession UserSession userSession) {
    service.create(command, userSession);
  }
}
