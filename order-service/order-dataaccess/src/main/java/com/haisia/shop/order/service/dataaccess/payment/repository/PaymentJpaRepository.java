package com.haisia.shop.order.service.dataaccess.payment.repository;

import com.haisia.shop.common.domain.valueobject.id.PaymentId;
import com.haisia.shop.order.service.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentJpaRepository extends JpaRepository<Payment, PaymentId> {
}
