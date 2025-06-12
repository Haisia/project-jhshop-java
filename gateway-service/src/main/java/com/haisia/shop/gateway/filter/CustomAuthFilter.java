package com.haisia.shop.gateway.filter;

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
    final String CUSTOM_HTTP_HEADER_USER_AUTH_ID = "X-UserAuth-Id";

    ServerHttpRequest request = exchange.getRequest();

    ServerHttpRequest.Builder mutatedRequestBuilder = request.mutate();
    if (request.getHeaders().containsKey(CUSTOM_HTTP_HEADER_USER_AUTH_ID)) {
      mutatedRequestBuilder.headers(httpHeaders -> httpHeaders.remove(CUSTOM_HTTP_HEADER_USER_AUTH_ID));
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
      .uri("lb://AUTH-SERVICE/auth/token/validate")
      .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
      .retrieve()
      .onStatus(HttpStatusCode::isError, clientResponse ->
        Mono.error(new RuntimeException("Auth service returned an error: " + clientResponse.statusCode()))
      )
      .bodyToMono(new ParameterizedTypeReference<ResponseValidateToken>() {})
      .flatMap(responseData -> {
        if (responseData != null) {
          ResponseValidateToken validationResult = responseData;

          if (validationResult.getData() != null) {
            ServerHttpRequest finalMutatedRequest = requestAfterDefense.mutate()
              .header(CUSTOM_HTTP_HEADER_USER_AUTH_ID, String.valueOf(validationResult.getData().getUserAuthId()))
              .build();
            return chain.filter(exchange.mutate().request(finalMutatedRequest).build());
          }
        }
        return chain.filter(exchange.mutate().request(requestAfterDefense).build());
      })
      .onErrorResume(
        Throwable.class,
        e -> {
          e.printStackTrace();
          System.out.println();
          return chain.filter(exchange.mutate().request(requestAfterDefense).build());
        }
      );
  }

  @Getter
  private static class ResponseValidateToken {
    private Data data;

    @Getter
    private static class Data {
      private UUID userAuthId;
    }
  }
}