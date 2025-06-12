package com.haisia.shop.user.service.dataaccess.userprofile.repository;

import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.common.domain.valueobject.id.UserProfileId;
import com.haisia.shop.user.service.domain.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileJpaRepository extends JpaRepository<UserProfile, UserProfileId> {
  Optional<UserProfile> findByUserAuthId(UserAuthId userAuthId);
}
