package com.haisia.shop.common.domain;

import com.haisia.shop.common.domain.entity.BaseEntity;

public interface DomainService<T extends BaseEntity<?>> {
  Object validateAndInitiate(T domain);
}
