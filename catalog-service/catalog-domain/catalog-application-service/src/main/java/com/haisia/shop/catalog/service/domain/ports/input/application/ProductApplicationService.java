package com.haisia.shop.catalog.service.domain.ports.input.application;

import com.haisia.shop.catalog.service.domain.dto.create.CreateProductCommand;
import com.haisia.shop.catalog.service.domain.dto.create.CreateProductResponse;
import com.haisia.shop.common.domain.valueobject.UserSession;

public interface ProductApplicationService {

  CreateProductResponse create(CreateProductCommand command, UserSession userSession);

}
