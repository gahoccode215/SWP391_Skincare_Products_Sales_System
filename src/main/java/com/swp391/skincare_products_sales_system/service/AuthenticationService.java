package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.*;
import com.swp391.skincare_products_sales_system.dto.response.LoginResponse;
import com.swp391.skincare_products_sales_system.dto.response.RefreshTokenResponse;
import com.swp391.skincare_products_sales_system.dto.response.RegisterResponse;

public interface AuthenticationService {
    LoginResponse login(LoginRequest request);
    RefreshTokenResponse refreshToken(RefreshTokenRequest request);
    RegisterResponse register(RegisterRequest request);
    void logout(LogoutRequest request);
    void changePassword(ChangePasswordRequest request);
}
