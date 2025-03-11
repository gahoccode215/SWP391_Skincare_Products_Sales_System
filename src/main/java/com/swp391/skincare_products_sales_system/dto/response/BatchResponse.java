package com.swp391.skincare_products_sales_system.dto.response;

import com.swp391.skincare_products_sales_system.entity.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchResponse {
    String id;
    String batchCode;
    Integer quantity;
    LocalDate manufactureDate;
    LocalDate expirationDate;
}
