package com.haisia.shop.auth.service.dataaccess.userloginrecord.adapter;

import com.haisia.shop.auth.service.dataaccess.userloginrecord.repository.UserLoginRecordJpaRepository;
import com.haisia.shop.auth.service.domain.ports.output.repository.UserLoginRecordRepository;
import com.haisia.shop.auth.service.domain.userloginrecord.entity.UserLoginRecord;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@RequiredArgsConstructor
@Component
public class UserLoginRecordRepositoryImpl implements UserLoginRecordRepository {

  private final UserLoginRecordJpaRepository repository;

  @Override
  public UserLoginRecord save(UserLoginRecord userLoginRecord) {
    return repository.save(userLoginRecord);
  }

  @Override
  public boolean existsByUserAuthIdAndCreatedAtBetween(UserAuthId userAuthId, Instant startTime, Instant endTime) {
    return repository.existsByUserAuthIdAndCreatedAtBetween(userAuthId, startTime, endTime);
  }
}
