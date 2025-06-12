package com.haisia.shop.auth.service.dataaccess.userloginrecord.repository;

import com.haisia.shop.auth.service.domain.userloginrecord.entity.UserLoginRecord;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface UserLoginRecordJpaRepository extends JpaRepository<UserLoginRecord, UUID> {
  boolean existsByUserAuthIdAndCreatedAtBetween(UserAuthId userAuthId, Instant startTime, Instant endTime);
}
