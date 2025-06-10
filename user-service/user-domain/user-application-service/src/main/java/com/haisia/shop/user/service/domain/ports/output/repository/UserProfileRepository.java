package com.haisia.shop.user.service.domain.ports.output.repository;

import com.haisia.shop.user.service.domain.entity.UserProfile;

public interface UserProfileRepository {
  UserProfile save(UserProfile userProfile);
}
