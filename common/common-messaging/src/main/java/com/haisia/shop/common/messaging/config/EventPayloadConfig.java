package com.haisia.shop.common.messaging.config;

import com.haisia.shop.common.domain.event.payload.EventPayload;
import com.haisia.shop.common.domain.event.payload.OrderCreatedEventPayload;
import com.haisia.shop.common.domain.event.payload.UserLoggedInFirstTodayEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.haisia.shop.common.messaging.EventTopicNameConstants.ORDER_CREATED;
import static com.haisia.shop.common.messaging.EventTopicNameConstants.USER_LOGGED_IN_FIRST_TODAY;

@RequiredArgsConstructor
@Configuration
public class EventPayloadConfig {

  @Bean
  public Map<String, Class<? extends EventPayload>> payloadTypeMap() {
    Map<String, Class<? extends EventPayload>> map = new HashMap<>();
    map.put(USER_LOGGED_IN_FIRST_TODAY, UserLoggedInFirstTodayEventPayload.class);
    map.put(ORDER_CREATED, OrderCreatedEventPayload.class);
    return map;
  }
}