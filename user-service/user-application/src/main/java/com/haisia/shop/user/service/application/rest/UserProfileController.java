package com.haisia.shop.user.service.application.rest;

import com.haisia.shop.common.application.annotation.InternalOnly;
import com.haisia.shop.common.application.dto.ResponseData;
import com.haisia.shop.user.service.domain.dto.create.CreateUserProfileCommand;
import com.haisia.shop.user.service.domain.dto.create.CreateUserProfileResponse;
import com.haisia.shop.user.service.domain.ports.input.application.UserProfileApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user/profile")
public class UserProfileController {

  private final UserProfileApplicationService userProfileApplicationService;

  @InternalOnly
  @PostMapping
  public ResponseEntity<ResponseData<CreateUserProfileResponse>> createUserProfile(
    @RequestBody CreateUserProfileCommand command
    ) {
    CreateUserProfileResponse response = userProfileApplicationService.createUserProfile(command);
    log.info("UserProfile 을 생성하였습니다. userProfileId: {}", response.userProfileId());
    ResponseData<CreateUserProfileResponse> data = ResponseData.success(response);
    return ResponseEntity.ok(data);
  }
}
