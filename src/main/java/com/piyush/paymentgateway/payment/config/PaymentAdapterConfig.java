package com.piyush.paymentgateway.payment.config;

import com.piyush.paymentgateway.common.enums.PaymentMethod;
import com.piyush.paymentgateway.payment.gateway.PaymentAdapter;
import com.piyush.paymentgateway.payment.gateway.adapter.CardPaymentAdapter;
import com.piyush.paymentgateway.payment.gateway.adapter.NetBankingAdapter;
import com.piyush.paymentgateway.payment.gateway.adapter.UpiPaymentAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class PaymentAdapterConfig {

    @Bean
    public Map<PaymentMethod, PaymentAdapter> paymentAdapterMap(){
        return Map.of(
                PaymentMethod.CARD, new CardPaymentAdapter(),
                PaymentMethod.NETBANKING, new NetBankingAdapter(),
                PaymentMethod.UPI, new UpiPaymentAdapter()

        );
    }
}
