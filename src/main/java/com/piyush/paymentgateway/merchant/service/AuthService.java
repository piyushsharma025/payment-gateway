package com.piyush.paymentgateway.merchant.service;

import com.piyush.paymentgateway.merchant.dto.request.MerchantSignupRequest;
import com.piyush.paymentgateway.merchant.dto.response.MerchantResponse;

public interface AuthService {

    MerchantResponse signup(MerchantSignupRequest request);
}
