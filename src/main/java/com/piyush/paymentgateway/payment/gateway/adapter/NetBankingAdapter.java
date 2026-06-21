package com.piyush.paymentgateway.payment.gateway.adapter;

import com.piyush.paymentgateway.payment.gateway.PaymentAdapter;
import com.piyush.paymentgateway.payment.gateway.dto.PaymentRequest;
import com.piyush.paymentgateway.payment.gateway.dto.PaymentResult;

public class NetBankingAdapter implements PaymentAdapter {
    @Override
    public PaymentResult initiate(PaymentRequest request) {
        return null;
    }
}
