package com.haisia.shop.auth.service.domain.refreshtokenrecord;

import com.haisia.shop.auth.service.domain.refreshtokenrecord.entity.RefreshTokenRecord;
import com.haisia.shop.auth.service.domain.refreshtokenrecord.entity.RefreshTokenRecordInitializer;
import com.haisia.shop.auth.service.domain.refreshtokenrecord.exception.RefreshTokenRecordDomainException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RefreshTokenRecordDomainServiceImpl implements RefreshTokenRecordDomainService {
  @Override
  public void validateAndInitiate(RefreshTokenRecord refreshTokenRecord) {
    RefreshTokenRecordInitializer initializer =
      new RefreshTokenRecordInitializer(refreshTokenRecord, RefreshTokenRecordDomainException::new);

    initializer.validateAndInitialize();
    log.info("RefreshToken 이 발급되었습니다. id: {}", refreshTokenRecord.getId().getValue());
  }
}
