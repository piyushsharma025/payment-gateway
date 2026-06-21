package com.piyush.paymentgateway.payment.config;

import com.piyush.paymentgateway.common.enums.PaymentMethod;
import com.piyush.paymentgateway.payment.processor.PaymentProcessor;
import com.piyush.paymentgateway.payment.processor.strategy.CardPaymentProcessor;
import com.piyush.paymentgateway.payment.processor.strategy.NetBankingPaymentProcessor;
import com.piyush.paymentgateway.payment.processor.strategy.UpiPaymentProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaymentProcessorConfig {

    @Bean
    public Map<PaymentMethod, PaymentProcessor> paymentProcessorMap(){
        return Map.of(
                PaymentMethod.CARD, new CardPaymentProcessor(),
                PaymentMethod.UPI, new UpiPaymentProcessor(),
                PaymentMethod.NETBANKING, new NetBankingPaymentProcessor()
        );
    }


}
