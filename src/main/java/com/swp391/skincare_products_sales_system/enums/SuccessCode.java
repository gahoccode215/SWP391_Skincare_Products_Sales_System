package com.swp391.skincare_products_sales_system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SuccessCode {
    GET_SUCCESS(200, "Get successfully.", HttpStatus.OK),
    CREATED_SUCCESS(2001, "Create successfully.", HttpStatus.CREATED),
    UPDATE_SUCCESS(2002, "Update successfully.", HttpStatus.OK),
    DELETED_SUCCESS(2003, "Deleted successfully.", HttpStatus.OK);
    private int code;
    private String message;
    private HttpStatus httpStatus;


}
