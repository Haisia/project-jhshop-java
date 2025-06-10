package com.haisia.shop.common.domain.dto.userprofile.create;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateUserProfileResponse(
  @NotNull UUID userProfileId
  ) {
}
