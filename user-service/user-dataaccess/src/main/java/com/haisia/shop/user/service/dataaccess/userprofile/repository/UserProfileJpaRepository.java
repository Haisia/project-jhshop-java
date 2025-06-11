package com.haisia.shop.user.service.dataaccess.userprofile.repository;

import com.haisia.shop.user.service.dataaccess.userprofile.entity.UserProfileJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileJpaRepository extends JpaRepository<UserProfileJpaEntity, UUID> {
  Optional<UserProfileJpaEntity> findByUserAuthId(UUID userAuthId);
}
