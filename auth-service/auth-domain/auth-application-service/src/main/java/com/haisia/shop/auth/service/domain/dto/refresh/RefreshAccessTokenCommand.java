package com.haisia.shop.auth.service.domain.dto.refresh;

import jakarta.validation.constraints.NotNull;

public record RefreshAccessTokenCommand(
  @NotNull String refreshToken
) {
}
