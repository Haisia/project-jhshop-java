package com.haisia.shop.auth.service.domain.dto.register;

import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RegisterUserCommand(
  @NotNull String email,
  @NotNull String password,
  @NotNull Address address,
  @NotNull PhoneNumber phoneNumber
) {
}
