package com.haisia.shop.common.messaging.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haisia.shop.common.messaging.EventPayloadFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class EventPayloadConfig {

  private final ObjectMapper objectMapper;

  public EventPayloadFactory eventPayloadFactory() {
    return new EventPayloadFactory(objectMapper);
  }

}
