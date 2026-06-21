package com.piyush.paymentgateway.payment.mapper;

import com.piyush.paymentgateway.payment.dto.response.OrderResponse;
import com.piyush.paymentgateway.payment.entity.OrderRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderResponse toResponse(OrderRecord order);
}
