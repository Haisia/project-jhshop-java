package com.haisia.shop.user.service.domain.handler.helper;

import com.haisia.shop.user.service.domain.UserProfileDomainService;
import com.haisia.shop.common.domain.dto.userprofile.create.CreateUserProfileCommand;
import com.haisia.shop.user.service.domain.entity.UserProfile;
import com.haisia.shop.user.service.domain.event.UserProfileCreatedEvent;
import com.haisia.shop.user.service.domain.exception.UserProfileDomainException;
import com.haisia.shop.user.service.domain.mapper.UserProfileDataMapper;
import com.haisia.shop.user.service.domain.ports.output.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserProfileCreateHelper {

  private final UserProfileDomainService domainService;
  private final UserProfileDataMapper mapper;
  private final UserProfileRepository repository;

  @Transactional
  public UserProfileCreatedEvent persist(CreateUserProfileCommand command) {
    UserProfile userProfile = mapper.createUserProfileCommandToUserProfile(command);
    UserProfileCreatedEvent event = domainService.validateAndInitiate(userProfile);

    UserProfile savedUserProfile = repository.save(userProfile);
    if (savedUserProfile == null) {
      throw new UserProfileDomainException("UserProfile 저장에 실패하였습니다.");
    }

    log.info("UserProfile 를 저장하였습니다. id: {}", userProfile.getId().getValue());
    return event;
  }

}
