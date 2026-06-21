package com.piyush.paymentgateway.payment.processor.strategy;

import com.piyush.paymentgateway.payment.processor.PaymentProcessor;
import com.piyush.paymentgateway.payment.processor.dto.PaymentProcessorRequest;
import com.piyush.paymentgateway.payment.processor.dto.PaymentProcessorResponse;

public class UpiPaymentProcessor implements PaymentProcessor {
    @Override
    public PaymentProcessorResponse charge(PaymentProcessorRequest request) {
        return null;
    }
}
