package com.haisia.shop.infrastructure.outbox.config;

import com.haisia.shop.infrastructure.outbox.OutboxMessageFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OutboxConfig {

  @Bean
  public OutboxMessageFactory outboxMessageFactory() {
    return new OutboxMessageFactory();
  }

}
