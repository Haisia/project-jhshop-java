package com.haisia.shop.auth.service.dataaccess.refreshtokenrecord.mapper;

import com.haisia.shop.auth.service.dataaccess.refreshtokenrecord.entity.RefreshTokenRecordJpaEntity;
import com.haisia.shop.auth.service.domain.entity.RefreshTokenRecord;
import com.haisia.shop.common.domain.valueobject.id.RefreshTokenRecordId;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenRecordDataaccessMapper {

  public RefreshTokenRecord refreshTokenRecordJpaEntityToRefreshTokenRecord(
    RefreshTokenRecordJpaEntity jpaEntity
  ) {
    return RefreshTokenRecord.builder()
      .id(new RefreshTokenRecordId(jpaEntity.getId()))
      .token(jpaEntity.getToken())
      .userAuthId(new UserAuthId(jpaEntity.getUserAuthId()))
      .refreshCount(jpaEntity.getRefreshCount())
      .available(jpaEntity.isAvailable())
      .build();
  }

  public RefreshTokenRecordJpaEntity refreshTokenRecordToRefreshTokenRecordJpaEntity(
    RefreshTokenRecord refreshTokenRecord
  ) {
    return RefreshTokenRecordJpaEntity.builder()
      .id(refreshTokenRecord.getId().getValue())
      .token(refreshTokenRecord.getToken())
      .userAuthId(refreshTokenRecord.getUserAuthId().getValue())
      .refreshCount(refreshTokenRecord.getRefreshCount())
      .available(refreshTokenRecord.isAvailable())
      .build();
  }

}
