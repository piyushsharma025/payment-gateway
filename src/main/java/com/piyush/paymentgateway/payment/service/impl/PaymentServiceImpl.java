package com.piyush.paymentgateway.payment.service.impl;

import com.piyush.paymentgateway.common.enums.OrderStatus;
import com.piyush.paymentgateway.common.enums.PaymentStatus;
import com.piyush.paymentgateway.common.exceptions.BusinessRuleViolationException;
import com.piyush.paymentgateway.common.exceptions.ResourceNotFoundException;
import com.piyush.paymentgateway.payment.dto.request.PaymentInitateRequest;
import com.piyush.paymentgateway.payment.dto.response.PaymentResponse;
import com.piyush.paymentgateway.payment.entity.OrderRecord;
import com.piyush.paymentgateway.payment.entity.Payment;
import com.piyush.paymentgateway.payment.gateway.PaymentGatewayRouter;
import com.piyush.paymentgateway.payment.gateway.dto.PaymentRequest;
import com.piyush.paymentgateway.payment.gateway.dto.PaymentResult;
import com.piyush.paymentgateway.payment.mapper.PaymentMapper;
import com.piyush.paymentgateway.payment.repository.OrderRepository;
import com.piyush.paymentgateway.payment.repository.PaymentRepository;
import com.piyush.paymentgateway.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentGatewayRouter paymentGatewayRouter;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;


    @Override
    @Transactional
    public PaymentResponse initiate(UUID merchantId, PaymentInitateRequest request) {
        OrderRecord order = orderRepository.findByIdAndMerchantId(merchantId, request.orderId())
                .orElseThrow(()-> new ResourceNotFoundException("order", request.orderId()));

        if(order.getOrderStatus() != OrderStatus.CREATED && order.getOrderStatus() != OrderStatus.ATTEMPTED){
            throw new BusinessRuleViolationException("Order not payable", "order cannot accept in"+ order.getOrderStatus());
        }

        order.setOrderStatus(OrderStatus.ATTEMPTED);
        order.setAttempts(order.getAttempts()+1);

        Payment payment = Payment.builder()
                .order(order)
                .merchantId(merchantId)
                .amount(order.getAmount())
                .status(PaymentStatus.CREATED)
                .method(request.method())
                .methodDetails(request.methodDetails())
                .build();
        payment = paymentRepository.save(payment);

        PaymentRequest paymentRequest = new PaymentRequest(payment.getId(), request.orderId(),
                                        merchantId, order.getAmount(), request.method(),
                                        request.methodDetails());
        PaymentResult result = paymentGatewayRouter.initiate(paymentRequest);

        if(result instanceof PaymentResult.Pending(String registrationRef)){
            payment.setProcessorReference(registrationRef);
        } else if (result instanceof PaymentResult.Failure(String errorCode, String errorDescription)) {
            payment.setStatus(PaymentStatus.FAILED);
            payment.setErrorCode(errorCode);
            payment.setErrorDescription(errorDescription);
        }

        payment = paymentRepository.save(payment);
        orderRepository.save(order);

        return paymentMapper.toResponse(payment);
    }
}
