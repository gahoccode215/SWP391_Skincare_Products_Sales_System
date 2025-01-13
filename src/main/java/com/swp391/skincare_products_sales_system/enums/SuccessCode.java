package com.swp391.skincare_products_sales_system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SuccessCode {
    DELETED_SUCCESS(2001, "Deleted successfully. ", HttpStatus.OK);
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
