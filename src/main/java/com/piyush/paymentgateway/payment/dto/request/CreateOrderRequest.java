package com.piyush.paymentgateway.payment.dto.request;

import com.piyush.paymentgateway.common.entity.Money;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Map;

public record CreateOrderRequest (

    @NotNull
    Money amount,

    @Size(max = 100)
    String receipt,//orderId known to merchant

    Map<String, Object> notes,

    LocalDateTime expiresAt
)
{

}
