package com.haisia.shop.common.domain.event.payload;

import com.fasterxml.jackson.databind.JsonNode;

public interface EventPayload {
  JsonNode toJsonNode();
}
