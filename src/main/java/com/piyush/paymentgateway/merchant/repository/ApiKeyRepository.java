package com.piyush.paymentgateway.merchant.repository;

import com.piyush.paymentgateway.merchant.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApiKeyRepository extends JpaRepository<ApiKey, UUID> {
}
