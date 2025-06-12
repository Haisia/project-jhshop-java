package com.haisia.shop.auth.service.domain.ports.output.repository;

import com.haisia.shop.auth.service.domain.userloginrecord.entity.UserLoginRecord;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;

import java.time.Instant;

public interface UserLoginRecordRepository {
  UserLoginRecord save(UserLoginRecord userLoginRecord);

  boolean existsByUserAuthIdAndCreatedAtBetween(UserAuthId userAuthId, Instant startTime, Instant endTime);
}
