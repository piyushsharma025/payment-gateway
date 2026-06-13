package com.piyush.paymentgateway.merchant.service;

import com.piyush.paymentgateway.merchant.dto.request.CreateApiKeyRequest;
import com.piyush.paymentgateway.merchant.dto.response.ApiKeyCreateResponse;
import com.piyush.paymentgateway.merchant.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApiKeyService {
    ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest createApiKeyRequest);
}
