package com.piyush.paymentgateway.payment.dto.request;

import com.piyush.paymentgateway.common.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.UUID;

public record PaymentInitateRequest(
        @NotNull(message = "Order Id is required")
        UUID orderId,

        @NotNull(message = "Payment Method is required")
        PaymentMethod method,

        Map<String, Object> methodDetails
) {
}
