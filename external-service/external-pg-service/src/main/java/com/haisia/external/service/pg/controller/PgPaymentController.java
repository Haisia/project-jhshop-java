package com.haisia.external.service.pg.controller;

import com.haisia.external.service.pg.dto.CardInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PgPaymentController {

  @PostMapping("/payment")
  public ResponseEntity<PaymentStatus> payByCard(@RequestBody CardInfo cardInfo) {
    try {
      log.info("[ExternalPGService]결제 중... cardInfo: {}", cardInfo);
      Thread.sleep(5000L);
    } catch (InterruptedException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(PaymentStatus.FAIL);
    }
    log.info("[ExternalPGService]결제 완료");
    return ResponseEntity.ok(PaymentStatus.SUCCESS);
  }

  public enum PaymentStatus {
    SUCCESS,
    FAIL
  }
}
