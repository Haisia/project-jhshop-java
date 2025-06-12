package com.haisia.shop.auth.service.domain.ports.output.repository;

import com.haisia.shop.auth.service.domain.refreshtokenrecord.entity.RefreshTokenRecord;

import java.util.Optional;

public interface RefreshTokenRecordRepository {
  RefreshTokenRecord save(RefreshTokenRecord refreshTokenRecord);

  Optional<RefreshTokenRecord> findByRefreshToken(String refreshToken);
}
