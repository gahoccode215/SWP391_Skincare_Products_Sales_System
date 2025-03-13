package com.swp391.skincare_products_sales_system.dto.request;

import com.swp391.skincare_products_sales_system.enums.SkinType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerRequest {

    Long answerId;
    String answerText;
    SkinType skinType;
}
