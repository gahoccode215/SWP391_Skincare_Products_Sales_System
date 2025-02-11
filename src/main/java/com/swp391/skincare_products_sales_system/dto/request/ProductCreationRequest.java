package com.swp391.skincare_products_sales_system.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCreationRequest {
    @NotBlank(message = "product name can not blank")
    String name;
    Double price;
    String description;
//    String thumbnail;
//    String usageInstruction;
//    LocalDate expiryDate;
//    Long brand_id;
//    Long origin_id;
//    Long skin_type_id;
    String category_id;
//    Set<Long> feature_ids;
}
