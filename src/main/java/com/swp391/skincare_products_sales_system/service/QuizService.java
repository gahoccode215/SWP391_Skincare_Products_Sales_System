package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.QuizCreationRequest;
import com.swp391.skincare_products_sales_system.dto.request.QuizUpdateRequest;
import com.swp391.skincare_products_sales_system.dto.request.SubmitQuiz;
import com.swp391.skincare_products_sales_system.dto.response.QuizResponse;
import com.swp391.skincare_products_sales_system.entity.Quiz;
import com.swp391.skincare_products_sales_system.entity.Result;
import com.swp391.skincare_products_sales_system.enums.Status;

import java.util.List;

public interface QuizService {
    Quiz createQuiz(QuizCreationRequest quizRequest);
    void deleteQuiz(Long id);
    void changeStatusQuiz(Long id, Status status);
    Quiz getQuizById(Long id);
    void updateQuiz(QuizUpdateRequest request, Long id);
    List<QuizResponse> getAll(boolean admin);
    Result submitQuiz(SubmitQuiz submitQuiz, Long quiz);
}


