package com.piyush.paymentgateway.merchant.service.impl;

import com.piyush.paymentgateway.common.exceptions.ResourceNotFoundException;
import com.piyush.paymentgateway.common.util.Randomizer;
import com.piyush.paymentgateway.merchant.dto.request.CreateApiKeyRequest;
import com.piyush.paymentgateway.merchant.dto.response.ApiKeyCreateResponse;
import com.piyush.paymentgateway.merchant.dto.response.ApiKeyResponse;
import com.piyush.paymentgateway.merchant.entity.ApiKey;
import com.piyush.paymentgateway.merchant.entity.Merchant;
import com.piyush.paymentgateway.merchant.repository.ApiKeyRepository;
import com.piyush.paymentgateway.merchant.repository.MerchantRepository;
import com.piyush.paymentgateway.merchant.service.ApiKeyService;
import jakarta.annotation.Nullable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiKeyServiceImpl implements ApiKeyService  {

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;


    @Override
    @Transactional
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("merchant", merchantId));

        String keyId = "rzp_"+request.environment().name().toLowerCase()+"_"+ Randomizer.randomBase64(24);
        String rawSecret = Randomizer.randomBase64(40);

        ApiKey apiKey = ApiKey.builder()
                .merchant(merchant)
                .keyId(keyId)
                .keySecretHash(rawSecret) // TODO: encode with BcryptPasswordEncoder
                .environment(request.environment())
                .build();

        apiKey = apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(), keyId, rawSecret, request.environment());
    }

    @Override
    public List<ApiKeyResponse> listByMerchant(UUID merchantId) {
        return apiKeyRepository.findByMerchant_Id(merchantId).stream()
                .map(apiKey ->
                        new ApiKeyResponse(
                                apiKey.getId(),
                                apiKey.getKeyId(),
                                apiKey.getEnvironment(),
                                apiKey.isEnabled(),
                                apiKey.getLastUsedAt(), null))
                .toList();
    }

    @Override
    @Transactional
    public void revoke(UUID merchantId, UUID keyId) {
        ApiKey key = apiKeyRepository.findById(keyId)
                .filter( apiKey -> apiKey.getMerchant().getId().equals(merchantId))
                .orElseThrow(()-> new ResourceNotFoundException("ApiKey", keyId));

        key.setEnabled(false);
        apiKeyRepository.save(key);
    }

    @Override
    @Transactional
    public @Nullable ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId) {
        ApiKey apiKey = apiKeyRepository.findById(keyId).filter(apiKey1 -> apiKey1.getMerchant().getId().equals(merchantId))
                .orElseThrow(()-> new ResourceNotFoundException("ApiKey",keyId));

        String newRawSecret = Randomizer.randomBase64(40);
        apiKey.setPreviouskeySecretHash(apiKey.getKeySecretHash());
        apiKey.setKeySecretHash(newRawSecret); // TODO: encode with BcryptPasswordEncoder
        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodExpiresAt(LocalDateTime.now().plusHours(24));
        apiKey = apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(), apiKey.getKeyId(), newRawSecret, apiKey.getEnvironment());
    }


}
