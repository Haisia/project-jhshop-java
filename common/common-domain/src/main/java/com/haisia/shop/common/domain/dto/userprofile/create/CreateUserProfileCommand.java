package com.haisia.shop.common.domain.dto.userprofile.create;

import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateUserProfileCommand(
  @NotNull UUID userAuthId,
  @NotNull String email,
  @NotNull Address address,
  @NotNull PhoneNumber phoneNumber
) {
}
