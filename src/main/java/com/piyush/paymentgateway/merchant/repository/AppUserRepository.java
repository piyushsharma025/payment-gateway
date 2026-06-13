package com.piyush.paymentgateway.merchant.repository;

import com.piyush.paymentgateway.merchant.entity.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUsers, UUID> {
}
