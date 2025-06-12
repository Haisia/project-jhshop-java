package com.haisia.shop.common.domain.valueobject.id;

import java.io.Serializable;

public abstract class BaseId<T> implements Serializable {
  public abstract T getValue();
}
