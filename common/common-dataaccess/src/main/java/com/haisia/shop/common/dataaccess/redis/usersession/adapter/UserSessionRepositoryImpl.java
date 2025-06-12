package com.haisia.shop.common.dataaccess.redis.usersession.adapter;

import com.haisia.shop.common.dataaccess.redis.usersession.entity.UserSessionRedisEntity;
import com.haisia.shop.common.dataaccess.redis.usersession.mapper.UserSessionDataaccessMapper;
import com.haisia.shop.common.dataaccess.redis.usersession.repository.UserSessionRedisRepository;
import com.haisia.shop.common.domain.ports.output.repository.UserSessionRepository;
import com.haisia.shop.common.domain.valueobject.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserSessionRepositoryImpl implements UserSessionRepository {

  private final UserSessionRedisRepository repository;
  private final UserSessionDataaccessMapper mapper;

  @Override
  public UserSession save(UserSession userSession) {
    UserSessionRedisEntity entity = mapper.toEntity(userSession);
    return mapper.toPojo(repository.save(entity));
  }

  @Override
  public Optional<UserSession> findById(String key) {
    return repository.findById(key)
      .map(mapper::toPojo);
  }
}
