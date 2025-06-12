package com.haisia.shop.catalog.service.application.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog/seller")
public class SellerController {

  @PostMapping
  public void createSeller() {
    log.info("Hello world");
  }

}
