package com.haisia.shop.catalog.service.domain.dto.create;

import java.util.UUID;

public record CreateSellerCommand(
  UUID userAuthId
) {
}
