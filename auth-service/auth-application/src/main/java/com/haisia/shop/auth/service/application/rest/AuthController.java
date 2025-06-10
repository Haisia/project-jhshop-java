package com.haisia.shop.auth.service.application.rest;

import com.haisia.shop.auth.service.domain.dto.register.RegisterUserCommand;
import com.haisia.shop.auth.service.domain.dto.register.RegisterUserResponse;
import com.haisia.shop.auth.service.domain.ports.input.service.AuthApplicationService;
import com.haisia.shop.common.application.dto.ResponseData;
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
@RequestMapping("/auth")
public class AuthController {

  private final AuthApplicationService authApplicationService;

  @PostMapping("/register")
  public ResponseEntity<ResponseData<RegisterUserResponse>> registerUser(@RequestBody RegisterUserCommand command) {
    RegisterUserResponse response = authApplicationService.registerUser(command);
    log.info("회원가입이 완료되었습니다. userAuthId: {}", response.userAuthId());
    ResponseData<RegisterUserResponse> data = ResponseData.success(response);
    return ResponseEntity.ok(data);
  }

}
