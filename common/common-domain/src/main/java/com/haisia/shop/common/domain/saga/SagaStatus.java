package com.haisia.shop.common.domain.saga;

public enum SagaStatus {
  STARTED, FAILED, SUCCEEDED, PROCESSING, COMPENSATING, COMPENSATED
}
