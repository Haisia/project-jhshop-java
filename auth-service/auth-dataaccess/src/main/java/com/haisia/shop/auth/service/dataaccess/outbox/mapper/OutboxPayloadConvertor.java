package com.haisia.shop.auth.service.dataaccess.outbox.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.haisia.shop.common.domain.event.payload.EventPayload;

import java.util.function.Function;

public interface OutboxPayloadConvertor extends Function<JsonNode, EventPayload> {
}
