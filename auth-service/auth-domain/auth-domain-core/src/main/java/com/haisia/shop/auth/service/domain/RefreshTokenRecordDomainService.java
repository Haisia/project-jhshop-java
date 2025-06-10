package com.haisia.shop.auth.service.domain;

import com.haisia.shop.auth.service.domain.entity.RefreshTokenRecord;

public interface RefreshTokenRecordDomainService {
  RefreshTokenRecord initiate(RefreshTokenRecord refreshTokenRecord);
}
