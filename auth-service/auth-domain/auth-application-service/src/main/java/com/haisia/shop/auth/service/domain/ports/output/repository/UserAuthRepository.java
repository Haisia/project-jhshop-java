package com.haisia.shop.auth.service.domain.ports.output.repository;

import com.haisia.shop.auth.service.domain.entity.UserAuth;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;

import java.util.Optional;

public interface UserAuthRepository {
  UserAuth save(UserAuth userAuth);
  Optional<UserAuth> findById(UserAuthId userAuthId);
  Optional<UserAuth> findByEmail(String email);
}
