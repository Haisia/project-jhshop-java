package com.haisia.shop.common.dataaccess.redis.usersession.mapper;

import com.haisia.shop.common.dataaccess.redis.usersession.entity.UserSessionRedisEntity;
import com.haisia.shop.common.domain.valueobject.UserSession;
import org.springframework.stereotype.Component;

@Component
public class UserSessionDataaccessMapper {

  public UserSession toPojo(UserSessionRedisEntity entity) {
    return UserSession.builder()
      .userAuthId(entity.getKey())
      .email(entity.getEmail())
      .expiration(entity.getExpiration())
      .build();
  }

  public UserSessionRedisEntity toEntity(UserSession userSession) {
    return UserSessionRedisEntity.builder()
      .key(userSession.userAuthId())
      .email(userSession.email())
      .expiration(userSession.expiration())
      .build();
  }
}
