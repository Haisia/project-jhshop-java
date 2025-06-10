package com.haisia.shop.user.service.dataaccess.userprofile.mapper;

import com.haisia.shop.common.dataaccess.jpa.mapper.CommonDataaccessMapper;
import com.haisia.shop.common.domain.valueobject.Money;
import com.haisia.shop.common.domain.valueobject.PhoneNumber;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.common.domain.valueobject.id.UserProfileId;
import com.haisia.shop.user.service.dataaccess.userprofile.entity.UserProfileJpaEntity;
import com.haisia.shop.user.service.domain.entity.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserProfileDataaccessMapper {

  private final CommonDataaccessMapper commonDataaccessMapper;

  public UserProfile userProfileJpaEntityToUserProfile(UserProfileJpaEntity userProfileJpaEntity) {
    return UserProfile.builder()
      .userProfileId(new UserProfileId(userProfileJpaEntity.getId()))
      .userAuthId(new UserAuthId(userProfileJpaEntity.getUserAuthId()))
      .email(userProfileJpaEntity.getEmail())
      .address(commonDataaccessMapper.addressJpaVoToAddress(userProfileJpaEntity.getAddress()))
      .phoneNumber(new PhoneNumber(userProfileJpaEntity.getPhoneNumber()))
      .balance(new Money(userProfileJpaEntity.getBalance()))
      .build();
  }

  public UserProfileJpaEntity userProfileToUserProfileJpaEntity(UserProfile userProfile) {
    return UserProfileJpaEntity.builder()
      .id(userProfile.getId().getValue())
      .userAuthId(userProfile.getUserAuthId().getValue())
      .email(userProfile.getEmail())
      .address(commonDataaccessMapper.addressToAddressJpaVo(userProfile.getAddress()))
      .phoneNumber(userProfile.getPhoneNumber().number())
      .balance(userProfile.getBalance().amount())
      .build();
  }

}
