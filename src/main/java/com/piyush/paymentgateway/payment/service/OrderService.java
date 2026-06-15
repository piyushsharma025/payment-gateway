package com.piyush.paymentgateway.payment.service;

import com.piyush.paymentgateway.payment.dto.request.CreateOrderRequest;
import com.piyush.paymentgateway.payment.dto.response.OrderResponse;

import java.util.UUID;

public interface OrderService {
    OrderResponse create(UUID merchantId, CreateOrderRequest request);
}
