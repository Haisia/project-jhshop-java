package com.haisia.shop.user.service.domain.ports.output.repository;

import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.user.service.domain.entity.UserProfile;

import java.util.Optional;

public interface UserProfileRepository {
  UserProfile save(UserProfile userProfile);

  Optional<UserProfile> findByUserAuthId(UserAuthId userAuthId);
}
