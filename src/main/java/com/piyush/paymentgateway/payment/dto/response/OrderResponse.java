package com.piyush.paymentgateway.payment.dto.response;

import com.piyush.paymentgateway.common.entity.Money;
import com.piyush.paymentgateway.common.enums.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        UUID merchantId,
        String receipt,
        Money amount,
        OrderStatus status,
        Integer attempts,
        Map<String, Object> notes,
        LocalDateTime expiresAt,
        LocalDate createdAt
) {
}
