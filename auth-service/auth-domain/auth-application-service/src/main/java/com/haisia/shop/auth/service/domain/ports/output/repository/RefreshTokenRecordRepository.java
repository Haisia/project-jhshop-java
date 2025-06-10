package com.haisia.shop.auth.service.domain.ports.output.repository;

import com.haisia.shop.auth.service.domain.entity.RefreshTokenRecord;

public interface RefreshTokenRecordRepository {
  RefreshTokenRecord save(RefreshTokenRecord refreshTokenRecord);
}
