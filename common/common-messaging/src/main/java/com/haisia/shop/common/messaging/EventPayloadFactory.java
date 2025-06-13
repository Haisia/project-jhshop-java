package com.haisia.shop.common.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haisia.shop.common.domain.event.payload.EventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class EventPayloadFactory {

  private final ObjectMapper objectMapper;
  private final Map<String, Class<? extends EventPayload>> payloadTypeMap;

  private static final String KAFKA_TOPIC_NAME_KEY = "kafka_receivedTopic";

  public EventPayload from(Message<String> message) {
    try {
      Class<? extends EventPayload> clazz = extractType(message);
      if (clazz == null) throw new IllegalArgumentException("알맞는 EventPayload 타입을 찾을 수 없습니다.");
      return objectMapper.readValue(message.getPayload(), clazz);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private Class<? extends EventPayload> extractType(Message<?> message) {
    String typeKey = (String) message.getHeaders().get(KAFKA_TOPIC_NAME_KEY);
    return payloadTypeMap.get(typeKey);
  }
}