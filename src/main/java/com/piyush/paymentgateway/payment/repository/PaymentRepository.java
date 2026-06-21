package com.piyush.paymentgateway.payment.repository;

import com.piyush.paymentgateway.payment.entity.OrderRecord;
import com.piyush.paymentgateway.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    List<Payment> findByOrder_Id(OrderRecord order);
}
