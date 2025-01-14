package com.swp391.skincare_products_sales_system.exception;


import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import lombok.*;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    private ErrorCode errorCode;
    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
