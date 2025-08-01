package com.haisia.shop.auth.service.application.rest;

import com.haisia.shop.auth.service.domain.dto.login.LoginUserCommand;
import com.haisia.shop.auth.service.domain.dto.login.LoginUserResponse;
import com.haisia.shop.auth.service.domain.dto.refresh.RefreshAccessTokenCommand;
import com.haisia.shop.auth.service.domain.dto.refresh.RefreshAccessTokenResponse;
import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthCommand;
import com.haisia.shop.auth.service.domain.dto.create.CreateUserAuthResponse;
import com.haisia.shop.auth.service.domain.dto.update.UpdatePasswordCommand;
import com.haisia.shop.auth.service.domain.dto.validate.ValidateAccessTokenResponse;
import com.haisia.shop.auth.service.domain.ports.input.service.AuthApplicationService;
import com.haisia.shop.common.application.annotation.InjectUserSession;
import com.haisia.shop.common.application.dto.ResponseData;
import com.haisia.shop.common.domain.valueobject.UserSession;
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
  public ResponseEntity<ResponseData<CreateUserAuthResponse>> createUserAuth(@RequestBody CreateUserAuthCommand command) {
    CreateUserAuthResponse response = authApplicationService.createUserAuth(command);
    log.info("회원가입이 완료되었습니다. userAuthId: {}", response.userAuthId());
    ResponseData<CreateUserAuthResponse> data = ResponseData.success(response);
    return ResponseEntity.ok(data);
  }

  @PostMapping("/login")
  public ResponseEntity<ResponseData<LoginUserResponse>> loginUser(@RequestBody LoginUserCommand command) {
    LoginUserResponse response = authApplicationService.loginUser(command);
    log.info("로그인에 성공하였습니다. refreshToken: {}", response.refreshToken());
    ResponseData<LoginUserResponse> data = ResponseData.success(response);
    return ResponseEntity.ok(data);
  }

  @PatchMapping("/password")
  public ResponseEntity<ResponseData<Boolean>> updatePassword(
    @RequestBody UpdatePasswordCommand command,
    @InjectUserSession UserSession userSession
  ) {
    authApplicationService.updatePassword(command, userSession);
    return ResponseEntity.ok(ResponseData.success(true));
  }

  @PostMapping("/token/refresh")
  public ResponseEntity<ResponseData<RefreshAccessTokenResponse>> refreshAccessToken(
    @RequestBody RefreshAccessTokenCommand command
  ) {
    RefreshAccessTokenResponse response = authApplicationService.refreshAccessToken(command);
    log.info("AccessToken 발급에 성공하였습니다. accessToken: {}", response.accessToken());
    ResponseData<RefreshAccessTokenResponse> data = ResponseData.success(response);
    return ResponseEntity.ok(data);
  }

  @PostMapping("/token/validate")
  public ResponseEntity<?> validateAccessToken(@RequestHeader("Authorization") String token) {
    String accessToken = token.split(" ")[1];
    ValidateAccessTokenResponse response = authApplicationService.validateAccessToken(accessToken);
    ResponseData<ValidateAccessTokenResponse> data = ResponseData.success(response);
    return ResponseEntity.ok(data);
  }

}
