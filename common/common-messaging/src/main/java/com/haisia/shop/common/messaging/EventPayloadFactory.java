package com.haisia.shop.common.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haisia.shop.common.domain.event.payload.EventPayload;
import com.haisia.shop.common.domain.event.payload.UserLoggedInFirstTodayEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventPayloadFactory {

  private final ObjectMapper objectMapper;
  private static final String KAFKA_TOPIC_NAME_KEY = "kafka-receivedTopic";

  public EventPayload from(Message<?> message) {
    String topicName = (String) message.getHeaders().get(KAFKA_TOPIC_NAME_KEY);
    try {
      return (EventPayload) objectMapper.readValue((String) message.getPayload(), getPayloadClassByKafkaTopicName(topicName));
    } catch(Exception e) {
      return null;
    }
  }

  private Class<?> getPayloadClassByKafkaTopicName(String topicName) {
    if (topicName.equals("UserLoggedInFirstToday.event")) {
      return UserLoggedInFirstTodayEventPayload.class;
    }
    return null;
  }

}
