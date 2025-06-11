package com.haisia.shop.user.service.domain.service;

import com.haisia.shop.common.domain.event.payload.EventPayload;
import com.haisia.shop.common.domain.event.payload.UserLoggedInFirstTodayEventPayload;
import com.haisia.shop.common.domain.exception.DomainException;
import com.haisia.shop.common.domain.valueobject.id.UserAuthId;
import com.haisia.shop.user.service.domain.entity.UserProfile;
import com.haisia.shop.user.service.domain.exception.UserProfileDomainException;
import com.haisia.shop.user.service.domain.ports.input.message.UserLoggedInFirstTodayUsecase;
import com.haisia.shop.user.service.domain.ports.output.repository.EventPayloadRepository;
import com.haisia.shop.user.service.domain.ports.output.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserLoggedInFirstTodayService implements UserLoggedInFirstTodayUsecase {

  private final UserProfileRepository userProfileRepository;
  private final EventPayloadRepository eventPayloadRepository;

  @Override
  public void process(UserLoggedInFirstTodayEventPayload payload) {
    /*
     * 1. 페이로드 persist
     * 2. sagaId로 멱등성 보장하기
     * 3. 포인트 지급 도메인 이벤트 발행
     * */
    EventPayload savedEventPayload = eventPayloadRepository.save(payload);
    if (savedEventPayload == null) {
      throw new DomainException("EventPayload 저장에 실패하였습니다. sagaId: " + payload.getSagaId());
    }

    UserAuthId userAuthId = new UserAuthId(payload.getUserAuthId());
    UserProfile userProfile =
      userProfileRepository.findByUserAuthId(userAuthId)
        .orElseThrow(
          () -> new UserProfileDomainException("UserProfile 을 찾을 수 없습니다. userAuthId: " + userAuthId.getValue())
        );



  }

  @Override
  public void rollback(UserLoggedInFirstTodayEventPayload data) {

  }
}
