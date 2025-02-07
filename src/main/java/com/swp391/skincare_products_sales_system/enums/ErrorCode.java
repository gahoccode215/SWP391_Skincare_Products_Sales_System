package com.swp391.skincare_products_sales_system.enums;

import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USERNAME_EXISTED(1002, "Username existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    INVALID_LOGIN(1009, "Username or password not correct", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_EXISTED(1010, "Product not existed", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_EXISTED(1011, "Category not existed", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1012, "Token invalid", HttpStatus.UNAUTHORIZED),
    REGISTER_ERROR(1013, "Register error", HttpStatus.BAD_REQUEST),
    ACCESS_DENIED(1014, "Access Denied", HttpStatus.FORBIDDEN),
    INTERNAL_SERVER_ERROR(1015, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
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