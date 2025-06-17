package com.haisia.shop.order.service.client.http.webclient.externalpgservice.adapter;

import com.haisia.shop.order.service.client.http.webclient.externalpgservice.dto.CardInfo;
import com.haisia.shop.order.service.client.http.webclient.externalpgservice.exception.OrderServiceExternalPGServiceException;
import com.haisia.shop.order.service.client.http.webclient.externalpgservice.mapper.PaymentExternalPGServiceMapper;
import com.haisia.shop.order.service.domain.payment.entity.Payment;
import com.haisia.shop.order.service.domain.ports.output.client.ExternalPGClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExternalPGClientImpl implements ExternalPGClient {

  private final PaymentExternalPGServiceMapper mapper;
  private final WebClient externalPGServiceWebClient;

  @Override
  public boolean requestPayment(Payment payment) {
    try {
      CardInfo cardInfo = mapper.toCardInfo(payment);

      return Boolean.TRUE.equals(
        externalPGServiceWebClient
          .post()
          .uri("/payment")
          .body(Mono.just(cardInfo), cardInfo.getClass())
          .retrieve()
          .onStatus(
            HttpStatusCode::is4xxClientError,
            clientResponse -> clientResponse.bodyToMono(String.class)
              .flatMap(errorBody -> Mono.error(new OrderServiceExternalPGServiceException(String.format(
                  "클라이언트 오류: %s, 에러 메세지: %s",
                  clientResponse.statusCode(),
                  errorBody
                )))
              ))
          .onStatus(
            HttpStatusCode::is5xxServerError,
            clientResponse -> clientResponse.bodyToMono(String.class)
              .flatMap(errorBody -> Mono.error(new OrderServiceExternalPGServiceException(String.format(
                  "서버 오류: %s, 에러 메시지: %s",
                  clientResponse.statusCode(),
                  errorBody
                )))
              ))
          .bodyToMono(String.class)
          .map(responseBody -> {
            log.info("결제 응답: {}", responseBody);
            return true;
          })
          .block());
    } catch (WebClientResponseException e) {
      log.error("WebClient 오류: {}", e.getResponseBodyAsString());
      return false;
    } catch (Exception e) {
      log.error("결제 요청 중 알 수 없는 오류 발생: {}", e.getMessage());
      return false;
    }
  }
}