package com.haisia.shop.auth.service.domain.refreshtokenrecord;

import com.haisia.shop.auth.service.domain.refreshtokenrecord.entity.RefreshTokenRecord;

public interface RefreshTokenRecordDomainService {
  RefreshTokenRecord initiate(RefreshTokenRecord refreshTokenRecord);
}
