package com.haisia.shop.auth.service.dataaccess.userloginrecord.mapper;

import com.haisia.shop.auth.service.dataaccess.userloginrecord.entity.UserLoginRecordJpaEntity;
import com.haisia.shop.auth.service.domain.entity.UserLoginRecord;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.common.domain.valueobject.id.UserLoginRecordId;
import org.springframework.stereotype.Component;

@Component
public class UserLoginRecordDataaccessMapper {

  public UserLoginRecord userLoginRecordJpaEntityToUserLoginRecord(UserLoginRecordJpaEntity jpaEntity) {
    return UserLoginRecord.builder()
      .id(new UserLoginRecordId(jpaEntity.getId()))
      .userAuthId(new UserAuthId(jpaEntity.getUserAuthId()))
      .email(jpaEntity.getEmail())
      .succeedAt(jpaEntity.getSucceedAt())
      .ipAddress(jpaEntity.getIpAddress())
      .isFirstLoginOfDay(jpaEntity.isFirstLoginOfDay())
      .build();
  }

  public UserLoginRecordJpaEntity userLoginRecordToUserLoginRecordJpaEntity(UserLoginRecord userLoginRecord) {
    return UserLoginRecordJpaEntity.builder()
      .id(userLoginRecord.getId().getValue())
      .userAuthId(userLoginRecord.getUserAuthId().getValue())
      .email(userLoginRecord.getEmail())
      .succeedAt(userLoginRecord.getSucceedAt())
      .ipAddress(userLoginRecord.getIpAddress())
      .isFirstLoginOfDay(userLoginRecord.isFirstLoginOfDay())
      .build();
  }

}
