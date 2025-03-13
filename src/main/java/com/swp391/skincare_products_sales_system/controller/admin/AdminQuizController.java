package com.swp391.skincare_products_sales_system.controller.admin;

import com.swp391.skincare_products_sales_system.dto.request.QuizCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.dto.response.QuizResponse;
import com.swp391.skincare_products_sales_system.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/quizs")
@RequiredArgsConstructor
@Tag(name = "Quiz Controller")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminQuizController {
    QuizService quizService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Tạo mới quiz"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STAFF')")
    public ApiResponse<QuizResponse> createQuiz(@RequestBody @Valid QuizCreationRequest request) {
        return ApiResponse.<QuizResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Tạo mới quiz thành công")
                .result(quizService.createQuiz(request))
                .build();
    }

    @DeleteMapping("/delete-quiz/{quizId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Xóa câu hỏi", description = "API Xóa câu hỏi")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STAFF')")
    public ApiResponse<Void> deleteQuiz(@PathVariable Long quizId) {
        quizService.deleteQuiz(quizId);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Xóa câu hỏi thành công")
                .build();
    }

}
