package com.swp391.skincare_products_sales_system.dto.response;

import com.swp391.skincare_products_sales_system.entity.Question;
import com.swp391.skincare_products_sales_system.entity.Result;
import com.swp391.skincare_products_sales_system.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizResponse {
    Long id;
    String description;
    String title;
    Status status;
    private List<Question> questions;  // Thêm câu hỏi vào response
    private List<Result> results;
}
