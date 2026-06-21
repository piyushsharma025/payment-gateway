package com.piyush.paymentgateway.payment.controller;

import com.piyush.paymentgateway.payment.dto.request.PaymentInitateRequest;
import com.piyush.paymentgateway.payment.dto.response.PaymentResponse;
import com.piyush.paymentgateway.payment.service.PaymentService;
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
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    UUID merchantId = UUID.fromString("2a44355b-05e9-49c0-996c-263c0d2e3c90");

    @PostMapping
    public ResponseEntity<PaymentResponse> initiate(@Valid  @RequestBody PaymentInitateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.initiate(merchantId, request));
    }
}
