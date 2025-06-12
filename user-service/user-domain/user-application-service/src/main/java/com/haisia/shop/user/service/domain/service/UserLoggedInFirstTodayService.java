package com.haisia.shop.user.service.domain.service;

import com.haisia.shop.common.domain.event.payload.UserLoggedInFirstTodayEventPayload;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.user.service.domain.UserProfileDomainService;
import com.haisia.shop.user.service.domain.entity.UserProfile;
import com.haisia.shop.user.service.domain.exception.UserProfileDomainException;
import com.haisia.shop.user.service.domain.ports.input.message.UserLoggedInFirstTodayUsecase;
import com.haisia.shop.user.service.domain.ports.output.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserLoggedInFirstTodayService implements UserLoggedInFirstTodayUsecase {

  private final UserProfileRepository userProfileRepository;
  private final UserProfileDomainService userProfileDomainService;

  @Transactional
  @Override
  public void process(UserLoggedInFirstTodayEventPayload payload) {
    UserAuthId userAuthId = new UserAuthId(payload.getUserAuthId());
    UserProfile userProfile =
      userProfileRepository.findByUserAuthId(userAuthId)
        .orElseThrow(
          () -> new UserProfileDomainException("UserProfile 을 찾을 수 없습니다. userAuthId: " + userAuthId.getValue())
        );

    userProfileDomainService.awardPointForFirstLoginToday(userProfile);
  }

  @Transactional
  @Override
  public void rollback(UserLoggedInFirstTodayEventPayload payload) {

  }
}
