package com.haisia.shop.common.domain.saga;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SagaFailureStatus {
  SagaStatus value() default SagaStatus.FAILED;
}