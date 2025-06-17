package com.haisia.external.service.pg.controller;

import com.haisia.external.service.pg.dto.CardInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PgPaymentController {

  @PostMapping("/payment")
  public void payByCard(@RequestBody CardInfo cardInfo) {
    try {
      log.info("[ExternalPGService]결제 중... cardInfo: {}", cardInfo);
      Thread.sleep(5000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info("[ExternalPGService]결제 완료");
  }
}
