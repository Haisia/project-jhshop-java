package com.haisia.shop.auth.service.dataaccess.userloginrecord.repository;

import com.haisia.shop.auth.service.dataaccess.userloginrecord.entity.UserLoginRecordJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface UserLoginRecordJpaRepository extends JpaRepository<UserLoginRecordJpaEntity, UUID> {
  boolean existsByUserAuthIdAndCreatedAtBetween(UUID userAuthId, Instant startTime, Instant endTime);
}
