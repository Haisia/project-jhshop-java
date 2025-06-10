package com.haisia.shop.auth.service.domain.handler;

import com.haisia.shop.auth.service.domain.config.JwtTokenProvider;
import com.haisia.shop.auth.service.domain.dto.refresh.RefreshAccessTokenCommand;
import com.haisia.shop.auth.service.domain.dto.refresh.RefreshAccessTokenResponse;
import com.haisia.shop.auth.service.domain.entity.RefreshTokenRecord;
import com.haisia.shop.auth.service.domain.exception.RefreshTokenRecordDomainException;
import com.haisia.shop.auth.service.domain.mapper.RefreshTokenRecordDataMapper;
import com.haisia.shop.auth.service.domain.ports.output.repository.RefreshTokenRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class RefreshAccessTokenCommandHandler {

  private final JwtTokenProvider refreshTokenProvider;
  private final JwtTokenProvider accessTokenProvider;
  private final RefreshTokenRecordRepository repository;

  @Transactional
  public RefreshAccessTokenResponse refreshAccessToken(RefreshAccessTokenCommand command) {
    /*
     * 1. 토큰검증
     * 2. 도메인검증
     * 3. 엑세스토큰발급
     * 4. 발급횟수 증가, persist
     * */

    String refreshToken = command.refreshToken();
    if (!refreshTokenProvider.validate(refreshToken)) {
      throw new RefreshTokenRecordDomainException("유효하지 않은 토큰입니다. refreshToken: " + refreshToken);
    }

    RefreshTokenRecord refreshTokenRecord = repository.findByRefreshToken(refreshToken)
      .orElseThrow(() -> new RefreshTokenRecordDomainException("유효하지 않은 토큰입니다. refreshToken: " + refreshToken));

    if (!refreshTokenRecord.isAvailable()) {
      throw new RefreshTokenRecordDomainException("유효하지 않은 토큰입니다. refreshToken: " + refreshToken);
    }

    String generatedAccessToken = accessTokenProvider.generate(
      refreshTokenProvider.getUserAuthId(refreshToken),
      refreshTokenProvider.getEmail(refreshToken)
    );

    refreshTokenRecord.addRefreshCount();
    RefreshTokenRecord savedRefreshTokenRecord = repository.save(refreshTokenRecord);
    if (savedRefreshTokenRecord == null) {
      throw new RefreshTokenRecordDomainException("RefreshTokenRecord 저장에 실패하였습니다.");
    }

    return new RefreshAccessTokenResponse(generatedAccessToken);
  }

}
