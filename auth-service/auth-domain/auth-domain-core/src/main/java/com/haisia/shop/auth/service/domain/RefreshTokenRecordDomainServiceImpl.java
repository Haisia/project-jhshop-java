package com.haisia.shop.auth.service.domain;

import com.haisia.shop.auth.service.domain.entity.RefreshTokenRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RefreshTokenRecordDomainServiceImpl implements RefreshTokenRecordDomainService {

  @Override
  public RefreshTokenRecord initiate(RefreshTokenRecord refreshTokenRecord) {
    refreshTokenRecord.initialize();
    refreshTokenRecord.setAvailable(true);
    log.info("RefreshToken 이 발급되었습니다. id: {}", refreshTokenRecord.getId().getValue());
    return refreshTokenRecord;
  }
}
