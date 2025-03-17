package com.swp391.skincare_products_sales_system.controller;

import com.swp391.skincare_products_sales_system.dto.request.SubmitQuiz;
import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.dto.response.QuizResponse;
import com.swp391.skincare_products_sales_system.entity.Quiz;
import com.swp391.skincare_products_sales_system.entity.Result;
import com.swp391.skincare_products_sales_system.service.QuizService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizs")
@RequiredArgsConstructor
@Tag(name = "Quiz Controller")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuizController {

    QuizService quizService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<QuizResponse>> getAll() {
        return ApiResponse.<List<QuizResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh sách câu hỏi thành công")
                .result(quizService.getAll(false))
                .build();
    }
    @PostMapping("/submit/{quizId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Result> submitQuiz(@Valid @PathVariable Long quizId, @RequestBody SubmitQuiz submitQuiz) {
        return ApiResponse.<Result>builder()
                .code(HttpStatus.OK.value())
                .message("Làm trắc nghiệm thành công")
                .result(quizService.submitQuiz(submitQuiz, quizId))
                .build();
    }
}
