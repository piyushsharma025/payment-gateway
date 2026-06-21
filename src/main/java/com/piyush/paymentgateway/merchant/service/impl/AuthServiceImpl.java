package com.piyush.paymentgateway.merchant.service.impl;

import com.piyush.paymentgateway.common.enums.MerchantStatus;
import com.piyush.paymentgateway.common.enums.UserRole;
import com.piyush.paymentgateway.common.exceptions.DuplicateResourceException;
import com.piyush.paymentgateway.merchant.dto.request.MerchantSignupRequest;
import com.piyush.paymentgateway.merchant.dto.response.MerchantResponse;
import com.piyush.paymentgateway.merchant.entity.AppUsers;
import com.piyush.paymentgateway.merchant.entity.Merchant;
import com.piyush.paymentgateway.merchant.mapper.MerchantMapper;
import com.piyush.paymentgateway.merchant.repository.AppUserRepository;
import com.piyush.paymentgateway.merchant.repository.MerchantRepository;
import com.piyush.paymentgateway.merchant.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;

    private final MerchantRepository merchantRepository;

    private final MerchantMapper merchantMapper;


    @Override
    public MerchantResponse signup(MerchantSignupRequest request) {
        if(merchantRepository.existsByEmail(request.email())){
            throw new DuplicateResourceException("DUPLICATE_MERCHANT", "Merchant with email already exists: "+request.email());
        }
//        Merchant merchant = Merchant.builder()
//                .businessName(request.businessName())
//                .businessType(request.businessType())
//                .name(request.name())
//                .email(request.email())
//                .status(MerchantStatus.PENDING_KYC)
//                .build();

        Merchant merchant = merchantMapper.toEntityFromSignUpRequest(request);
        merchant.setStatus(MerchantStatus.PENDING_KYC);
        merchant = merchantRepository.save(merchant);

        AppUsers appUser = AppUsers.builder()
                .email(request.email())
                .merchant(merchant)
                .passwordHash(request.password()) // TODO: encrypt using bcrypt
                .role(UserRole.OWNER)
                .build();

        appUserRepository.save(appUser);

//        return new MerchantResponse(merchant.getId(), merchant.getName(),merchant.getEmail(),
//                merchant.getBusinessName(),merchant.getBusinessType(),merchant.getStatus());

        return merchantMapper.toResponse(merchant);
    }
}
