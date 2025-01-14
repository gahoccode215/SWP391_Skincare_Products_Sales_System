package com.swp391.skincare_products_sales_system.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorResponse {
    private Date timeStamp;
    private int status;
    private String path;
    private String error;
    private String message;
}
