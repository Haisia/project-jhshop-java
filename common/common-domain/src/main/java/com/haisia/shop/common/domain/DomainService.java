package com.haisia.shop.common.domain;

import com.haisia.shop.common.domain.entity.BaseEntity;
import com.haisia.shop.common.domain.event.DomainEvent;

public interface DomainService<T extends BaseEntity<?>> {
  DomainEvent validateAndInitiate(T domain);
}
