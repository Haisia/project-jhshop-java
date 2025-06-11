package com.haisia.shop.common.domain.outbox.config;

import com.haisia.shop.common.domain.outbox.OutboxMessageFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OutboxConfig {

  @Bean
  public OutboxMessageFactory outboxMessageFactory() {
    return new OutboxMessageFactory();
  }

}
