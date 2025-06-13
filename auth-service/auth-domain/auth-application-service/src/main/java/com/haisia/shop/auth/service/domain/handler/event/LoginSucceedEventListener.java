package com.haisia.shop.auth.service.domain.handler.event;

import com.haisia.shop.auth.service.domain.ports.output.repository.OutboxMessageRepository;
import com.haisia.shop.auth.service.domain.ports.output.repository.UserLoginRecordRepository;
import com.haisia.shop.auth.service.domain.userloginrecord.UserLoginRecordDomainService;
import com.haisia.shop.auth.service.domain.userloginrecord.entity.UserLoginRecord;
import com.haisia.shop.auth.service.domain.userloginrecord.event.UserLoginSucceedEvent;
import com.haisia.shop.common.domain.event.payload.UserLoggedInFirstTodayEventPayload;
import com.haisia.shop.common.domain.outbox.OutboxMessage;
import com.haisia.shop.common.domain.outbox.OutboxMessageFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

import static com.haisia.shop.common.domain.DomainConstants.UTC;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginSucceedEventListener {

  private final UserLoginRecordRepository userLoginRecordRepository;
  private final OutboxMessageRepository outboxMessageRepository;
  private final OutboxMessageFactory outboxMessageFactory;
  private final UserLoginRecordDomainService userLoginRecordDomainService;

  @Async
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void handleLoginSucceed(UserLoginSucceedEvent event) {

    UserLoginRecord newRecord = UserLoginRecord.builder()
      .userAuthId(event.getUserAuthId())
      .email(event.getEmail())
      .succeedAt(event.getCreatedAt().toInstant())
      .ipAddress(event.getIpAddress())
      .isFirstLoginOfDay(false)
      .build();

    userLoginRecordDomainService.validateAndInitiate(newRecord);

    LocalDate today = event.getCreatedAt().toLocalDate();
    boolean hasPriorLoginToday = userLoginRecordRepository.existsByUserAuthIdAndCreatedAtBetween(
      event.getUserAuthId(),
      today.atStartOfDay(ZoneId.of(UTC)).toInstant(),
      today.plusDays(1).atStartOfDay(ZoneId.of(UTC)).toInstant()
    );

    if (!hasPriorLoginToday) {
      newRecord.setFirstLoginOfDay(true);

      UserLoggedInFirstTodayEventPayload userLoggedInFirstTodayEventPayload =
        UserLoggedInFirstTodayEventPayload.builder()
          .sagaId(UUID.randomUUID())
          .aggregateId(newRecord.getId().getValue())
          .userAuthId(event.getUserAuthId().getValue())
          .loggedInTime(event.getCreatedAt().toLocalDateTime())
          .build();

      OutboxMessage outboxMessage = outboxMessageFactory.create(userLoggedInFirstTodayEventPayload);

      outboxMessageRepository.save(outboxMessage);
    }

    userLoginRecordRepository.save(newRecord);
  }
}