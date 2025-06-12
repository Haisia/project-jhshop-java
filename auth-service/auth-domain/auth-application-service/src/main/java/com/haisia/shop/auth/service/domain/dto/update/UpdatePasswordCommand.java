package com.haisia.shop.auth.service.domain.dto.update;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdatePasswordCommand(
  @NotNull String newPassword
) {
}
