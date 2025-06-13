package com.haisia.shop.order.service.application.rest;

import com.haisia.shop.common.application.annotation.InjectUserSession;
import com.haisia.shop.common.domain.valueobject.UserSession;
import com.haisia.shop.order.service.domain.dto.create.CreateOrderCommand;
import com.haisia.shop.order.service.domain.ports.input.application.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

  private final OrderApplicationService service;

  @PostMapping
  public void create(@RequestBody CreateOrderCommand command, @InjectUserSession UserSession userSession) {
    service.create(command, userSession);
  }

}
