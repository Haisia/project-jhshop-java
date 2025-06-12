package com.haisia.shop.user.service.dataaccess.userprofile.adapter;

import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
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

  @Override
  public UserProfile save(UserProfile userProfile) {
    return repository.save(userProfile);
  }

  @Override
  public Optional<UserProfile> findByUserAuthId(UserAuthId userAuthId) {
    return repository.findByUserAuthId(userAuthId);
  }
}
