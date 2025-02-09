package com.swp391.skincare_products_sales_system.enums;

import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_EXISTED(1001, "Username existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1002, "User not existed", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_EXISTED(1003, "Product not existed", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_EXISTED(1004, "Category not existed", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1100, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    INVALID_KEY(1001, "Invalid uncategorized error", HttpStatus.BAD_REQUEST),
    INVALID_LOGIN(1102, "Username or password not correct", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1103, "Token invalid", HttpStatus.UNAUTHORIZED),
    USERNAME_INVALID(1104, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1105, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    BIRTHDAY_INVALID(1106, "Birthday must be at least {min} years", HttpStatus.BAD_REQUEST),
    GENDER_INVALID(1107, "Gender invalid", HttpStatus.BAD_REQUEST),
    REGISTER_ERROR(1103, "Register error", HttpStatus.BAD_REQUEST),
    ACCESS_DENIED(1104, "Access Denied", HttpStatus.FORBIDDEN),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}