package com.haisia.shop.auth.service.application.rest;

import com.haisia.shop.auth.service.domain.dto.login.LoginUserCommand;
import com.haisia.shop.auth.service.domain.dto.login.LoginUserResponse;
import com.haisia.shop.auth.service.domain.dto.refresh.RefreshAccessTokenCommand;
import com.haisia.shop.auth.service.domain.dto.refresh.RefreshAccessTokenResponse;
import com.haisia.shop.auth.service.domain.dto.register.RegisterUserCommand;
import com.haisia.shop.auth.service.domain.dto.register.RegisterUserResponse;
import com.haisia.shop.auth.service.domain.ports.input.service.AuthApplicationService;
import com.haisia.shop.common.application.dto.ResponseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping("/login")
  public ResponseEntity<ResponseData<LoginUserResponse>> loginUser(@RequestBody LoginUserCommand command) {
    LoginUserResponse response = authApplicationService.loginUser(command);
    log.info("로그인에 성공하였습니다. refreshToken: {}", response.refreshToken());
    ResponseData<LoginUserResponse> data = ResponseData.success(response);
    return ResponseEntity.ok(data);
  }

  @PostMapping("/refresh")
  public ResponseEntity<ResponseData<RefreshAccessTokenResponse>> refreshAccessToken(
    @RequestBody RefreshAccessTokenCommand command
  ) {
    RefreshAccessTokenResponse response = authApplicationService.refreshAccessToken(command);
    log.info("AccessToken 발급에 성공하였습니다. accessToken: {}", response.accessToken());
    ResponseData<RefreshAccessTokenResponse> data = ResponseData.success(response);
    return ResponseEntity.ok(data);
  }

}
