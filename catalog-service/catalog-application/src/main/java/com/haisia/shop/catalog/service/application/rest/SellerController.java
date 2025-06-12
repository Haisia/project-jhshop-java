package com.haisia.shop.catalog.service.application.rest;

import com.haisia.shop.catalog.service.domain.dto.create.CreateSellerCommand;
import com.haisia.shop.catalog.service.domain.ports.input.application.SellerApplicationService;
import com.haisia.shop.common.application.annotation.InjectUserSession;
import com.haisia.shop.common.domain.valueobject.UserSession;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog/seller")
public class SellerController {

  private final SellerApplicationService sellerApplicationService;

  @PostMapping
  public void createSeller(@InjectUserSession UserSession userSession) {
    CreateSellerCommand command = new CreateSellerCommand(UUID.fromString(userSession.userAuthId()));
    sellerApplicationService.create(command);
  }

}
