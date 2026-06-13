package com.piyush.paymentgateway.merchant.dto.response;

import com.piyush.paymentgateway.common.enums.BusinessType;
import com.piyush.paymentgateway.common.enums.MerchantStatus;

import java.util.UUID;

public record MerchantResponse(
        UUID id,
        String name,
        String email,
        String businessName,
        BusinessType businessType,
        MerchantStatus merchantStatus
) {
}