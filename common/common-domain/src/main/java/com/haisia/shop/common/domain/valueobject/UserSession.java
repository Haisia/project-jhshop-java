package com.haisia.shop.common.domain.valueobject;

import lombok.Builder;

@Builder
public record UserSession(
  String userAuthId,
  String email,
  Long expiration
) {
}
