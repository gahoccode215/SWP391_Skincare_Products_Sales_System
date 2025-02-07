package com.swp391.skincare_products_sales_system.controller;

import com.swp391.skincare_products_sales_system.dto.request.LoginRequest;
import com.swp391.skincare_products_sales_system.dto.request.LogoutRequest;
import com.swp391.skincare_products_sales_system.dto.request.RefreshTokenRequest;
import com.swp391.skincare_products_sales_system.dto.request.RegisterRequest;
import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.dto.response.LoginResponse;
import com.swp391.skincare_products_sales_system.dto.response.RefreshTokenResponse;
import com.swp391.skincare_products_sales_system.dto.response.RegisterResponse;
import com.swp391.skincare_products_sales_system.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "Login feature", description = "Input username and password to login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        return ApiResponse.<LoginResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Login successfully")
                .result(authenticationService.login(request))
                .build();
    }

    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get new Access Token by old token", description = "API retrieve old token to get new Access Token, then invalidated old token")
    ApiResponse<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<RefreshTokenResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Get new Access Token by old token successfully")
                .result(result).build();
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register new account", description = "API retrieve user attribute to create account")
    public ApiResponse<RegisterResponse> register(@RequestBody RegisterRequest request){
        return ApiResponse.<RegisterResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Register successfully")
                .result(authenticationService.register(request))
                .build();
    }
    @PostMapping("/logout")
    @Operation(summary = "Logout", description = "Invalidate JWT token to logout user")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<String> logout(@RequestBody LogoutRequest request) {
        authenticationService.logout(request);
        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message("Logout successfully")
                .build();
    }
}
