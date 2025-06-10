package com.haisia.shop.auth.service.domain.dto.login;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LoginUserCommand(
  @NotNull String email,
  @NotNull String password
) {
}
