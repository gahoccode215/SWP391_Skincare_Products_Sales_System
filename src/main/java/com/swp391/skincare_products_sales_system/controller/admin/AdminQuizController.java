package com.swp391.skincare_products_sales_system.controller.admin;

import com.swp391.skincare_products_sales_system.dto.request.QuizCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.QuizUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.response.ApiResponse;
import com.swp391.skincare_products_sales_system.dto.response.ProductResponse;
import com.swp391.skincare_products_sales_system.dto.response.QuizResponse;
import com.swp391.skincare_products_sales_system.entity.Quiz;
import com.swp391.skincare_products_sales_system.enums.Status;
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

import java.util.List;

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
    public ApiResponse<Quiz> createQuiz(@RequestBody @Valid QuizCreationRequest request) {
        return ApiResponse.<Quiz>builder()
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

    @PatchMapping("/change-status/{quizId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Đổi trạng thái sản phẩm câu hỏi")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STAFF')")
    public ApiResponse<Void> changeQuizStatus(@PathVariable Long quizId, @RequestParam Status status) {
        quizService.changeStatusQuiz(quizId, status);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Thay đổi trạng thái thành công")
                .build();
    }

    @GetMapping("/{quizId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STAFF')")
    public ApiResponse<Quiz> getQuizById(
            @PathVariable(required = false) Long quizId) {
        return ApiResponse.<Quiz>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy chi tiết câu hỏi thành công")
                .result(quizService.getQuizById(quizId))
                .build();
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STAFF')")
    public ApiResponse<List<QuizResponse>> getAll() {
        return ApiResponse.<List<QuizResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Lấy danh sách câu hỏi thành công")
                .result(quizService.getAll(true))
                .build();
    }
    @PutMapping("/{quizId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'STAFF')")
    public ApiResponse<List<QuizResponse>> updateQuiz(@RequestBody QuizUpdateRequest request, @PathVariable Long quizId) {
        quizService.updateQuiz(request, quizId);
        return ApiResponse.<List<QuizResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Cập nhật thành công")
                .build();
    }
}
