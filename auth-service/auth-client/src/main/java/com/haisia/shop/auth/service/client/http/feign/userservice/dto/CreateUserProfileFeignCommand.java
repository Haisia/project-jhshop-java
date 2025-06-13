package com.haisia.shop.auth.service.client.http.feign.userservice.dto;

import com.haisia.shop.common.domain.valueobject.Address;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateUserProfileFeignCommand (
  UUID userAuthId,
  String email,
  Address address,
  PhoneNumber phoneNumber
) {
}

