package com.swp391.skincare_products_sales_system.dto.response;


import com.swp391.skincare_products_sales_system.enums.Status;
import com.swp391.skincare_products_sales_system.model.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String id;
    String name;
    Double price;
    String description;
    String thumbnail;
    String slug;
    Status status;
    String category_id;
    String brand_id;
    Category category;
}