package com.piyush.paymentgateway.payment.processor.dto;

import com.piyush.paymentgateway.common.entity.Money;
import com.piyush.paymentgateway.common.enums.PaymentMethod;

import java.util.Map;

public record PaymentProcessorRequest(
        PaymentMethod method,
        Money amount,
        Map<String, Object> methodDetails

) {
}
