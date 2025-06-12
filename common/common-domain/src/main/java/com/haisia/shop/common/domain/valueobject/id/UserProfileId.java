package com.haisia.shop.common.domain.valueobject.id;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class UserProfileId extends BaseId<UUID> {
  private UUID value;
  public UserProfileId(UUID value) {
    super(value);
  }
}
