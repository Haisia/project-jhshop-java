package com.haisia.shop.auth.service.client.http.feign.userservice;

import com.haisia.shop.auth.service.client.http.feign.userservice.dto.CreateUserProfileFeignCommand;
import com.haisia.shop.common.domain.constants.CustomHttpHeaderConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service")
public interface UserServiceFeignClient {

  @PostMapping("/user/profile")
  ResponseEntity<?> createUserProfile(
    @RequestHeader(CustomHttpHeaderConstants.INTERNAL_ONLY) String secretKey,
    @RequestBody CreateUserProfileFeignCommand command
  );

}
