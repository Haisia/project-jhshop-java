package com.haisia.shop.auth.service.domain.dto.refresh;

import lombok.Builder;

@Builder
public record RefreshAccessTokenResponse(
  String accessToken
) {
}
