package com.haisia.shop.user.service.messaging.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haisia.shop.common.domain.event.payload.UserLoggedInFirstTodayEventPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserLoggedInFirstTodayMessageListener {

  private final ObjectMapper objectMapper;

  @Transactional
  @Bean
  public Consumer<Message<String>> handleUserLoggedInFirstToday() {
    return message -> {
//      log.info("############# {}", message);

      try {
        UserLoggedInFirstTodayEventPayload payload = objectMapper.readValue(message.getPayload(), UserLoggedInFirstTodayEventPayload.class);
        log.info("############# 페이로드 : {}", payload);
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }

    };
  }

}
