package com.haisia.shop.common.domain.saga;

import com.haisia.shop.common.domain.event.payload.EventPayload;
import com.haisia.shop.common.domain.exception.DomainException;
import com.haisia.shop.common.domain.ports.output.repository.EventPayloadRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

@RequiredArgsConstructor
@Aspect
@Component
public class SagaStepAspect {

  private final EventPayloadRepository eventPayloadRepository;
  private final SagaStatus DEFAULT_SAGA_STATUS = SagaStatus.FAILED;
  private final EventPayloadUpdater eventPayloadUpdater;

  @Transactional
  @Around("execution(* com.haisia.shop.common.domain.saga.SagaStep+.process(..)) && args(payload)")
  public Object handleSagaStepProcess(ProceedingJoinPoint joinPoint, EventPayload payload) throws Throwable {
    EventPayload savedEventPayload = eventPayloadRepository.save(payload);
    if (eventPayloadRepository.save(payload) == null) {
      throw new DomainException("EventPayload 저장에 실패하였습니다. sagaId: " + payload.getSagaId());
    }

    Object result;
    try {
      result = joinPoint.proceed();
      savedEventPayload.setSagaStatus(SagaStatus.SUCCEEDED);
    } catch (Throwable e) {
      setSagaStatusByAnnotation(joinPoint, savedEventPayload);
      savedEventPayload.addFailureMessage(e.getMessage());
      savedEventPayload.setAction(SagaAction.ROLLBACK);
      throw e;
    } finally {
      eventPayloadUpdater.update(savedEventPayload);
    }
    return result;
  }

  private void setSagaStatusByAnnotation(ProceedingJoinPoint joinPoint, EventPayload savedEventPayload) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();

    SagaStatus sagaStatus = DEFAULT_SAGA_STATUS;
    if (method.isAnnotationPresent(SagaFailureStatus.class)) {
      sagaStatus = method.getAnnotation(SagaFailureStatus.class).value();
    }
    savedEventPayload.setSagaStatus(sagaStatus);
  }
}