package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.LoginRequest;
import com.swp391.skincare_products_sales_system.dto.request.RefreshTokenRequest;
import com.swp391.skincare_products_sales_system.dto.response.LoginResponse;
import com.swp391.skincare_products_sales_system.dto.response.RefreshTokenResponse;

public interface AuthenticationService {
    LoginResponse login(LoginRequest request);
    RefreshTokenResponse refreshToken(RefreshTokenRequest request);
}
