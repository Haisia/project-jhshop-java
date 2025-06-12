package com.haisia.shop.catalog.service.application.rest;

import com.haisia.shop.catalog.service.domain.dto.create.CreateProductCommand;
import com.haisia.shop.catalog.service.domain.dto.create.CreateProductResponse;
import com.haisia.shop.catalog.service.domain.ports.input.application.ProductApplicationService;
import com.haisia.shop.common.application.annotation.InjectUserSession;
import com.haisia.shop.common.application.dto.ResponseData;
import com.haisia.shop.common.domain.valueobject.UserSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog/product")
public class ProductController {

  private final ProductApplicationService productApplicationService;

  @PostMapping
  public ResponseEntity<ResponseData<CreateProductResponse>> createProduct(
    @RequestBody CreateProductCommand command,
    @InjectUserSession UserSession userSession
  ) {
    CreateProductResponse response = productApplicationService.create(command, userSession);
    log.info("Product 가 생성되었습니다. productId: {}", response.productId());
    return ResponseEntity.ok(ResponseData.success(response));
  }
}
