package com.piyush.paymentgateway.payment.gateway;

import com.piyush.paymentgateway.payment.gateway.dto.PaymentRequest;
import com.piyush.paymentgateway.payment.gateway.dto.PaymentResult;
import org.springframework.stereotype.Component;

@Component
public interface PaymentAdapter {

    PaymentResult initiate(PaymentRequest request);
}
