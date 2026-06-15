package com.piyush.paymentgateway.merchant.service;

import com.piyush.paymentgateway.merchant.dto.request.CreateApiKeyRequest;
import com.piyush.paymentgateway.merchant.dto.response.ApiKeyCreateResponse;
import com.piyush.paymentgateway.merchant.dto.response.ApiKeyResponse;

import java.util.List;
import java.util.UUID;

public interface ApiKeyService {
    ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest createApiKeyRequest);

    List<ApiKeyResponse> listByMerchant(UUID merchantId);

    void revoke(UUID merchantId, UUID keyId);

    ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId);
}
