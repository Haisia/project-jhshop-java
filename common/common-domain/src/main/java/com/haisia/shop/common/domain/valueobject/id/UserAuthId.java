package com.haisia.shop.common.domain.valueobject.id;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class UserAuthId extends BaseId<UUID> {
  private UUID value;
  public UserAuthId(UUID value) {
    super(value);
  }
}
