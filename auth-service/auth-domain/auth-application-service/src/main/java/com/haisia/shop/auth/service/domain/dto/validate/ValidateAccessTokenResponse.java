package com.haisia.shop.auth.service.domain.dto.validate;

import java.util.UUID;

public record ValidateAccessTokenResponse(
  UUID userAuthId
  ) {
}
