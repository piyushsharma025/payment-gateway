package com.piyush.paymentgateway.payment.controller;

import com.piyush.paymentgateway.payment.dto.request.CreateOrderRequest;
import com.piyush.paymentgateway.payment.dto.response.OrderResponse;
import com.piyush.paymentgateway.payment.service.impl.OrderServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    UUID merchantId = UUID.fromString("2a44355b-05e9-49c0-996c-263c0d2e3c90");//TODO: replace it with Merchant Context

    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody CreateOrderRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(merchantId, request));

    }
}
