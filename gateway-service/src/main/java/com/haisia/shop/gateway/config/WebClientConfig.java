package com.haisia.shop.gateway.config;

import com.haisia.shop.gateway.filter.CustomAuthFilter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @LoadBalanced
  @Bean
  public WebClient.Builder loadBalancedWebClientBuilder() {
    return WebClient.builder();
  }

  @Bean
  public WebClient authWebClient(WebClient.Builder loadBalancedWebClientBuilder) {
    return loadBalancedWebClientBuilder.build();
  }

  @Bean
  public GlobalFilter customAuthFilter(WebClient authWebClient) {
    return new CustomAuthFilter(authWebClient);
  }
}
