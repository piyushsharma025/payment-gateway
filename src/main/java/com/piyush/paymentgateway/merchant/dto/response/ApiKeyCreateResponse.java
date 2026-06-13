package com.piyush.paymentgateway.merchant.dto.response;



import com.piyush.paymentgateway.common.enums.Environment;

import java.util.UUID;

public record ApiKeyCreateResponse(
        UUID id,
        String keyId,
        String keySecret,
        Environment environment
) {
}
