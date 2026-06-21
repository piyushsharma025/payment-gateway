package com.piyush.paymentgateway.payment.service;

import com.piyush.paymentgateway.payment.dto.request.PaymentInitateRequest;
import com.piyush.paymentgateway.payment.dto.response.PaymentResponse;

import java.util.UUID;

public interface PaymentService {

    PaymentResponse initiate(UUID merchantId, PaymentInitateRequest request);
}
