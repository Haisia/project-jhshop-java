package com.haisia.shop.user.service.messaging.listener;

import com.haisia.shop.common.domain.event.payload.UserLoggedInFirstTodayEventPayload;
import com.haisia.shop.common.messaging.EventPayloadFactory;
import com.haisia.shop.user.service.domain.ports.input.message.UserLoggedInFirstTodayUsecase;
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

  private final EventPayloadFactory eventPayloadFactory;
  private final UserLoggedInFirstTodayUsecase userLoggedInFirstTodayUsecase;

  @Transactional
  @Bean
  public Consumer<Message<String>> processUserLoggedInFirstToday() {
    return message -> {
      UserLoggedInFirstTodayEventPayload payload =
        (UserLoggedInFirstTodayEventPayload) eventPayloadFactory.from(message);

      userLoggedInFirstTodayUsecase.process(payload);
    };
  }

}
