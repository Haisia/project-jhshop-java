package com.haisia.shop.auth.service.domain.dto.create;

import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateUserAuthCommand(
  @NotNull String email,
  @NotNull String password,
  @NotNull Address address,
  @NotNull PhoneNumber phoneNumber
) {
}
