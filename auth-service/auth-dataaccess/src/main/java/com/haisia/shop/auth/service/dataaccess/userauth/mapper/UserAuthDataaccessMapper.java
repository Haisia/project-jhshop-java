package com.haisia.shop.auth.service.dataaccess.userauth.mapper;

import com.haisia.shop.auth.service.dataaccess.userauth.entity.UserAuthJpaEntity;
import com.haisia.shop.auth.service.domain.entity.UserAuth;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import org.springframework.stereotype.Component;

@Component
public class UserAuthDataaccessMapper {

  public UserAuthJpaEntity userAuthToJpaEntity(UserAuth userAuth) {
    return UserAuthJpaEntity.builder()
      .id(userAuth.getId().getValue())
      .email(userAuth.getEmail())
      .hashedPassword(userAuth.getHashedPassword())
      .build();
  }

  public UserAuth jpaEntityToUserAuth(UserAuthJpaEntity jpaEntity) {
    return UserAuth.builder()
      .userAuthId(new UserAuthId(jpaEntity.getId()))
      .email(jpaEntity.getEmail())
      .hashedPassword(jpaEntity.getHashedPassword())
      .build();
  }

}
