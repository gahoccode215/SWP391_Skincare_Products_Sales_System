package com.swp391.skincare_products_sales_system.service;

import com.swp391.skincare_products_sales_system.dto.request.QuizCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.QuizResponse;

public interface QuizService {
    QuizResponse createQuiz(QuizCreationRequest quizRequest);
    void deleteQuiz(Long id);
}


