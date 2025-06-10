package com.haisia.shop.auth.service.dataaccess.userauth.adapter;

import com.haisia.shop.auth.service.dataaccess.userauth.entity.UserAuthJpaEntity;
import com.haisia.shop.auth.service.dataaccess.userauth.mapper.UserAuthDataaccessMapper;
import com.haisia.shop.auth.service.dataaccess.userauth.repository.UserAuthJpaRepository;
import com.haisia.shop.auth.service.domain.entity.UserAuth;
import com.haisia.shop.auth.service.domain.ports.output.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserAuthRepositoryImpl implements UserAuthRepository {

  private final UserAuthJpaRepository repository;
  private final UserAuthDataaccessMapper mapper;

  @Override
  public UserAuth save(UserAuth userAuth) {
    UserAuthJpaEntity savedJpaEntity = repository.save(mapper.userAuthToJpaEntity(userAuth));
    return mapper.jpaEntityToUserAuth(savedJpaEntity);
  }
}
