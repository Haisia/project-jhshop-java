package com.haisia.shop.user.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateUserProfileResponse(
  @NotNull UUID userProfileId
  ) {
}
