package com.swp391.skincare_products_sales_system.enums;

import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    //10XX
    RESOURCE_NOT_FOUND(1000, "Resource not found", HttpStatus.NOT_FOUND),
    USERNAME_EXISTED(1001, "Username existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1002, "User not existed", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_EXISTED(1003, "Product not existed", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_EXISTED(1004, "Category not existed", HttpStatus.NOT_FOUND),
    IMAGE_NOT_FOUND(1005, "Image not found", HttpStatus.BAD_REQUEST),
    BRAND_NOT_EXISTED(1006, "Brand not existed", HttpStatus.BAD_REQUEST),
    ORIGIN_NOT_EXISTED(1007, "Origin not existed", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1008, "Role not found", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1009, "User existed", HttpStatus.BAD_REQUEST),
    ADDRESS_NOT_FOUND(1010, "Address not found", HttpStatus.BAD_REQUEST),
    CART_NOT_FOUND(1011, "Cart not found", HttpStatus.BAD_REQUEST),
    ORDER_NOT_FOUND(1012, "Order not found", HttpStatus.BAD_REQUEST),
    //11XX
    INVALID_KEY(1100, "Invalid uncategorized error", HttpStatus.BAD_REQUEST),
    INVALID_LOGIN(1101, "Username or password not correct", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1102, "Token invalid", HttpStatus.UNAUTHORIZED),
    INVALID_USERNAME(1103, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1104, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_BIRTHDAY(1105, "Birthday must be at least {min} years", HttpStatus.BAD_REQUEST),
    INVALID_CONFIRM_PASSWORD(1106, "Confirm password not match with password", HttpStatus.BAD_REQUEST),
    INVALID_CHANGE_PASSWORD(1007, "Old password not correct", HttpStatus.BAD_REQUEST),
    INVALID_GENDER(1107, "Gender invalid", HttpStatus.BAD_REQUEST),
    INVALID_QUANTITY(1108, "Quantity invalid", HttpStatus.BAD_REQUEST),
    INVALID_PAYMENT_METHOD(1109, "Invalid payment method", HttpStatus.BAD_REQUEST),
    INVALID_JSON(1010, "Json invalid", HttpStatus.BAD_REQUEST),
    // 12XX
    UNAUTHENTICATED(1201, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    // 13XX
    REGISTER_ERROR(1301, "Register failed", HttpStatus.BAD_REQUEST),
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