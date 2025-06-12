package com.haisia.shop.common.domain.valueobject.id;

import java.io.Serializable;
import java.util.Objects;

public abstract class BaseId<T> implements Serializable {
  private T value;

  protected BaseId(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    BaseId<?> baseId = (BaseId<?>) o;
    return Objects.equals(value, baseId.value);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }

  protected BaseId() {
  }
}
