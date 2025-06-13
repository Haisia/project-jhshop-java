package com.haisia.shop.order.service.domain.ports.input.application;

import com.haisia.shop.common.domain.valueobject.UserSession;
import com.haisia.shop.order.service.domain.dto.create.CreateOrderCommand;

public interface OrderApplicationService {

  void create(CreateOrderCommand command, UserSession userSession);

}
