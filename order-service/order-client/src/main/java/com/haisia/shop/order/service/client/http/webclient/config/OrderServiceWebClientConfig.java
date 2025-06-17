package com.haisia.shop.order.service.client.http.webclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OrderServiceWebClientConfig {

  @Bean
  public WebClient externalPGServiceWebClient() {
    return WebClient.builder()
      .baseUrl("http://localhost:9900")
      .build();
  }

}
