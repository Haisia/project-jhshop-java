package com.haisia.shop.common.domain.event.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;

import java.time.ZonedDateTime;
import java.util.UUID;

public record UserAuthCreatedEventPayload(
  @JsonProperty UUID sagaId,
  @JsonProperty ZonedDateTime createdAt,
  @JsonProperty String userAuthId,
  @JsonProperty String email,
  @JsonProperty Address address,
  @JsonProperty PhoneNumber phoneNumber
) implements EventPayload {
}
