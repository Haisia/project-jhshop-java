package com.haisia.shop.auth.service.domain.ports.output.repository;

import com.haisia.shop.auth.service.domain.entity.UserAuth;

import java.util.Optional;

public interface UserAuthRepository {
  Optional<UserAuth> save(UserAuth userAuth);
}
