package com.piyush.paymentgateway.merchant.mapper;

import com.piyush.paymentgateway.merchant.dto.response.ApiKeyCreateResponse;
import com.piyush.paymentgateway.merchant.dto.response.ApiKeyResponse;
import com.piyush.paymentgateway.merchant.entity.ApiKey;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiKeyMapper {

    ApiKeyCreateResponse toResponse(ApiKey apiKey);

    List<ApiKeyResponse> toResponseList(List<ApiKey> apiKeyList);
}
