package com.haisia.shop.auth.service.domain.dto.register;

import com.haisia.shop.auth.service.domain.entity.UserAuth;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record RegisterUserResponse(
  @NotNull UUID userAuthId
) {
  public static RegisterUserResponse from(UserAuth userAuth) {
    return new RegisterUserResponse(userAuth.getId().getValue());
  }
}