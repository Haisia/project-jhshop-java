package com.haisia.shop.auth.service.domain.ports.output.client.feign;

import com.haisia.shop.common.domain.dto.userprofile.create.CreateUserProfileCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static com.haisia.shop.common.domain.constants.CustomHttpHeaderConstants.INTERNAL_ONLY;

@FeignClient(name = "user-service")
public interface UserServiceClient {

  @PostMapping("/user/profile")
  ResponseEntity<?> createUserProfile(
    @RequestHeader(INTERNAL_ONLY) String secretKey,
    @RequestBody CreateUserProfileCommand command
  );

}
