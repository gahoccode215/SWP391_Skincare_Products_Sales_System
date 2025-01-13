package com.swp391.skincare_products_sales_system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    RESOURCE_NOT_FOUND_EXCEPTION(1001, "Resource not found exception.", HttpStatus.NOT_FOUND);
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
