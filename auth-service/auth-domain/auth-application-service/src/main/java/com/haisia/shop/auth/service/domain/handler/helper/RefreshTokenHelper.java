package com.haisia.shop.auth.service.domain.handler.helper;

import com.haisia.shop.auth.service.domain.RefreshTokenRecordDomainService;
import com.haisia.shop.auth.service.domain.entity.RefreshTokenRecord;
import com.haisia.shop.auth.service.domain.exception.RefreshTokenRecordDomainException;
import com.haisia.shop.auth.service.domain.ports.output.repository.RefreshTokenRecordRepository;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RefreshTokenHelper {
  private final RefreshTokenRecordDomainService refreshTokenRecordDomainService;
  private final RefreshTokenRecordRepository refreshTokenRecordRepository;

  public void initiateAndPersist(String refreshToken, UserAuthId userAuthId) {
    RefreshTokenRecord createdRefreshTokenRecord = RefreshTokenRecord.builder()
      .token(refreshToken)
      .userAuthId(userAuthId)
      .refreshCount(0)
      .build();
    refreshTokenRecordDomainService.initiate(createdRefreshTokenRecord);

    RefreshTokenRecord savedRefreshTokenRecord = refreshTokenRecordRepository.save(createdRefreshTokenRecord);
    if (savedRefreshTokenRecord == null) {
      throw new RefreshTokenRecordDomainException("RefreshTokenRecord 저장을 실패하였습니다.");
    }
  }
}
