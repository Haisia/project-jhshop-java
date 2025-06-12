package com.haisia.shop.common.domain.entity;

import com.haisia.shop.common.domain.valueobject.id.BaseId;

public abstract class AggregateRoot<ID extends BaseId<?>> extends BaseEntity<ID> {
}
