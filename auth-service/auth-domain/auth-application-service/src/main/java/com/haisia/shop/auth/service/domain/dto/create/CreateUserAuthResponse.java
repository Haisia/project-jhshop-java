package com.haisia.shop.auth.service.domain.dto.create;

import com.haisia.shop.auth.service.domain.userauth.entity.UserAuth;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateUserAuthResponse(
  @NotNull UUID userAuthId
) {
  public static CreateUserAuthResponse from(UserAuth userAuth) {
    return new CreateUserAuthResponse(userAuth.getId().getValue());
  }
}