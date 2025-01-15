package com.swp391.skincare_products_sales_system.exception;


import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import lombok.*;


public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
