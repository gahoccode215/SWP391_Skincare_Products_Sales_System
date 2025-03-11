package com.swp391.skincare_products_sales_system.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.swp391.skincare_products_sales_system.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductUpdateRequest {
    @NotBlank(message = "Tên không được để trống")
    String name;
    Double price;
    String description;
    String ingredient;
    String usageInstruction;
    SpecificationUpdateRequest specification;
    String thumbnail;
    Long brand_id;
    String category_id;
    Status status;
}
