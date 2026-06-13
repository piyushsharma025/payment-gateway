package com.piyush.paymentgateway.merchant.dto.request;


import com.piyush.paymentgateway.common.enums.Environment;

public record CreateApiKeyRequest(
        Environment environment
) {
}
