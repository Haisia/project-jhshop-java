package com.haisia.shop.common.domain.entity;

import com.haisia.shop.common.domain.valueobject.id.BaseId;

public abstract class AggregateRoot<ID extends BaseId<?>> extends BaseEntity<ID> {

  /**
  * @implNote 이 메서드는 반드시 protected 로 구현해야 하며,
  * 오직 @{@link com.haisia.shop.common.domain.AggregateRootInitializer} 의 하위객체에서만 호출되어야 합니다.
  * */
  protected abstract void initialize();
}
