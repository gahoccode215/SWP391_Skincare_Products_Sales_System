package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.entity.Otp;

public interface OtpService {
    Otp generateAndSaveOtp(String userId);
    boolean validateOtp(String userId, String otp);
    void resendOtp(String userId);
    void verifyOtp(String userId, String otpCode);
}
