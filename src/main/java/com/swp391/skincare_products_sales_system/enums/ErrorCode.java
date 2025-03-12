package com.swp391.skincare_products_sales_system.enums;

import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    //10XX
    RESOURCE_NOT_FOUND(1000, "Tài nguyên không tìm thấy", HttpStatus.NOT_FOUND),
    USERNAME_EXISTED(1001, "Tên tài khoản đã tồn tại", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1002, "Tài khoản không tồn tại", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND(1003, "Sản phẩm không tồn tại", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND(1004, "Danh mục không tồn tại", HttpStatus.NOT_FOUND),
    IMAGE_NOT_FOUND(1005, "Không tìm thấy ảnh", HttpStatus.BAD_REQUEST),
    BRAND_NOT_FOUND(1006, "Hãng không tồn tại", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1007, "Vai trò không tồn tại", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1008, "Tài khoản dùng đã tồn tại", HttpStatus.BAD_REQUEST),
    ADDRESS_NOT_FOUND(1009, "Địa chỉ không tồn tại", HttpStatus.BAD_REQUEST),
    CART_NOT_FOUND(1010, "Giỏ hàng không tồn tại", HttpStatus.BAD_REQUEST),
    ORDER_NOT_FOUND(1011, "Đơn hàng không tồn tại", HttpStatus.BAD_REQUEST),
    BATCH_NOT_FOUND(1012, "Lô hàng không tồn tại", HttpStatus.BAD_REQUEST),
    BLOG_NOT_FOUND(1013, "Blog không tồn tại", HttpStatus.BAD_REQUEST),
    VOUCHER_NOT_FOUND(1014,"Voucher không tồn tại", HttpStatus.BAD_REQUEST),
    INVALID_EXCHANGE_VOUCHER(1015, "Chỉ được sở hữu 1 Voucher mỗi loại", HttpStatus.BAD_REQUEST),
    NOT_ENOUGH_POINT(1016, "Không đủ điểm", HttpStatus.BAD_REQUEST),
    EMAIL_SEND_FAILED(1017, "Lỗi khi gửi email", HttpStatus.BAD_REQUEST),
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