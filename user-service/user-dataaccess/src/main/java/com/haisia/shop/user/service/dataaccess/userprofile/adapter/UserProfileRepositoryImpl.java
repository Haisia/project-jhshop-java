package com.haisia.shop.user.service.dataaccess.userprofile.adapter;

import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.user.service.dataaccess.userprofile.entity.UserProfileJpaEntity;
import com.haisia.shop.user.service.dataaccess.userprofile.mapper.UserProfileDataaccessMapper;
import com.haisia.shop.user.service.dataaccess.userprofile.repository.UserProfileJpaRepository;
import com.haisia.shop.user.service.domain.entity.UserProfile;
import com.haisia.shop.user.service.domain.ports.output.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserProfileRepositoryImpl implements UserProfileRepository {

  private final UserProfileJpaRepository repository;
  private final UserProfileDataaccessMapper mapper;

  @Override
  public UserProfile save(UserProfile userProfile) {
    UserProfileJpaEntity savedEntity = repository.save(mapper.toJpaEntity(userProfile));
    return mapper.toPojo(savedEntity);
  }

  @Override
  public Optional<UserProfile> findByUserAuthId(UserAuthId userAuthId) {
    return repository.findByUserAuthId(userAuthId.getValue())
      .map(mapper::toPojo);
  }
}
