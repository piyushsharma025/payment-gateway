package com.piyush.paymentgateway.payment.processor;

import com.piyush.paymentgateway.payment.processor.dto.PaymentProcessorRequest;
import com.piyush.paymentgateway.payment.processor.dto.PaymentProcessorResponse;

public interface PaymentProcessor {

    PaymentProcessorResponse charge(PaymentProcessorRequest request);
}
