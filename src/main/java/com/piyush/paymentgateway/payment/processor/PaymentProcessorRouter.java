package com.piyush.paymentgateway.payment.processor;

import com.piyush.paymentgateway.common.enums.PaymentMethod;
import com.piyush.paymentgateway.payment.processor.dto.PaymentProcessorRequest;
import com.piyush.paymentgateway.payment.processor.dto.PaymentProcessorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentProcessorRouter {

    Map<PaymentMethod, PaymentProcessor> paymentProcessors;

    public PaymentProcessorResponse charge(PaymentProcessorRequest request){
        PaymentProcessor processor = paymentProcessors.get(request.method());
        if(processor == null){
            throw new IllegalArgumentException("No payment processor registered for method: "+ request.method());
        }
        return processor.charge(request);
    }
}
