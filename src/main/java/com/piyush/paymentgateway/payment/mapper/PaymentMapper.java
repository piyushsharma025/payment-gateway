package com.piyush.paymentgateway.payment.mapper;

import com.piyush.paymentgateway.common.enums.PaymentEvent;
import com.piyush.paymentgateway.payment.dto.response.PaymentResponse;
import com.piyush.paymentgateway.payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMapper {

    @Mapping(target = "orderId", source = "order.id")
    PaymentResponse toResponse(Payment payment);

    @Mapping(target = "orderId", source = "order.id")
    List<PaymentResponse> toResponseList(List<Payment> paymentList);

}
