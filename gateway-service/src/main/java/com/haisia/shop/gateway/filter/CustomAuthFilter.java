package com.haisia.shop.gateway.filter;

import com.haisia.shop.common.application.dto.ResponseData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CustomAuthFilter implements GlobalFilter {

  private final WebClient authWebClient;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();

    ServerHttpRequest.Builder mutatedRequestBuilder = request.mutate();
    if (request.getHeaders().containsKey("X-User-Id")) {
      mutatedRequestBuilder.headers(httpHeaders -> httpHeaders.remove("X-User-Id"));
    }
    ServerHttpRequest requestAfterDefense = mutatedRequestBuilder.build();

    List<String> authorizationHeaders = requestAfterDefense.getHeaders().get(HttpHeaders.AUTHORIZATION);

    if (authorizationHeaders == null
      || authorizationHeaders.isEmpty()
      || !authorizationHeaders.getFirst().startsWith("Bearer ")
    ) {
      return chain.filter(exchange.mutate().request(requestAfterDefense).build());
    }

    String authorizationHeader = authorizationHeaders.getFirst();

    return authWebClient.post()
      .uri("lb://AUTH-SERVER/auth/v1/token/validate")
      .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
      .retrieve()
      .onStatus(HttpStatusCode::isError, clientResponse ->
        Mono.error(new RuntimeException("Auth service returned an error: " + clientResponse.statusCode()))
      )
      .bodyToMono(new ParameterizedTypeReference<ResponseData<ResponseValidateToken>>() {})
      .flatMap(responseData -> {
        if (responseData != null && responseData.getData() != null) {
          ResponseValidateToken validationResult = responseData.getData();

          if (validationResult.getUserId() != null) {
            ServerHttpRequest finalMutatedRequest = requestAfterDefense.mutate()
              .header("X-User-Id", String.valueOf(validationResult.getUserId()))
              .build();
            return chain.filter(exchange.mutate().request(finalMutatedRequest).build());
          }
        }
        return chain.filter(exchange.mutate().request(requestAfterDefense).build());
      })
      .onErrorResume(Throwable.class, e -> chain.filter(exchange.mutate().request(requestAfterDefense).build()));
  }

  @Getter
  private static class ResponseValidateToken {
    private UUID userId;
  }
}