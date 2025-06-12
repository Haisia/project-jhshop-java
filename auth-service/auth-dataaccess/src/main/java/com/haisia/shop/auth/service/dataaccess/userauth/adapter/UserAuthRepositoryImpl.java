package com.haisia.shop.auth.service.dataaccess.userauth.adapter;

import com.haisia.shop.auth.service.dataaccess.userauth.repository.UserAuthJpaRepository;
import com.haisia.shop.auth.service.domain.entity.UserAuth;
import com.haisia.shop.auth.service.domain.ports.output.repository.UserAuthRepository;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserAuthRepositoryImpl implements UserAuthRepository {

  private final UserAuthJpaRepository repository;

  @Override
  public UserAuth save(UserAuth userAuth) {
    return repository.save(userAuth);
  }

  @Override
  public Optional<UserAuth> findById(UserAuthId userAuthId) {
    return repository.findById(userAuthId);
  }

  @Override
  public Optional<UserAuth> findByEmail(String email) {
    return repository.findByEmail(email);
  }
}
