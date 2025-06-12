package com.haisia.shop.catalog.service.domain.ports.input.application;

import com.haisia.shop.catalog.service.domain.dto.create.CreateSellerCommand;

public interface SellerApplicationService {
  void create(CreateSellerCommand command);
}
