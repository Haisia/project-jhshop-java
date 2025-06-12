package com.haisia.shop.common.domain.saga;

import com.haisia.shop.common.domain.event.payload.EventPayload;
import com.haisia.shop.common.domain.exception.DomainException;
import com.haisia.shop.common.domain.ports.output.repository.EventPayloadRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Aspect
@Component
public class SagaStepAspect {

  private final EventPayloadRepository eventPayloadRepository;

  @Around("execution(* com.haisia.shop.common.domain.saga.SagaStep.process(..)) && args(payload)")
  public Object handleSagaStepProcess(ProceedingJoinPoint joinPoint, EventPayload payload) throws Throwable {
    EventPayload savedEventPayload = eventPayloadRepository.save(payload);
    if (savedEventPayload == null) {
      throw new DomainException("EventPayload 저장에 실패하였습니다. sagaId: " + payload.getSagaId());
    }

    Object result;
    try {
      result = joinPoint.proceed();
      savedEventPayload.setSagaStatus(SagaStatus.SUCCEEDED);
    } catch (Throwable e) {
      savedEventPayload.setSagaStatus(SagaStatus.FAILED);
      throw e;
    } finally {
      eventPayloadRepository.save(savedEventPayload);
    }
    return result;
  }
}
