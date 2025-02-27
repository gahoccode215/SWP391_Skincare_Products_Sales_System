package com.swp391.skincare_products_sales_system.dto.response;

import com.swp391.skincare_products_sales_system.enums.Status;
import com.swp391.skincare_products_sales_system.model.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    String id;
    String name;
    String description;
    String thumbnail;
    String slug;
    Status status;
}
