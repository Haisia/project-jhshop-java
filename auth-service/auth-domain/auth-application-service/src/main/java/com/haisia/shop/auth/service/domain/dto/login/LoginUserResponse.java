package com.haisia.shop.auth.service.domain.dto.login;

import lombok.Builder;

@Builder
public record LoginUserResponse(
  String refreshToken,
  String accessToken
) {
}
