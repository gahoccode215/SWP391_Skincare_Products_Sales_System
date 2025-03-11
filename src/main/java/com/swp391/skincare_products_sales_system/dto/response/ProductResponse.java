package com.swp391.skincare_products_sales_system.dto.response;


import com.swp391.skincare_products_sales_system.entity.Brand;
import com.swp391.skincare_products_sales_system.enums.Status;
import com.swp391.skincare_products_sales_system.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

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
    String ingredient;
    String usageInstruction;
    Double rating;
    LocalDate expirationTime;
    SpecificationResponse specification;
    Integer stock;
    String slug;
    Status status;
    Category category;
    Brand brand;
    List<FeedBackResponse> feedBacks;
}