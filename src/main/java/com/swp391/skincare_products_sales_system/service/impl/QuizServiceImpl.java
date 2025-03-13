package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.dto.request.AnswerRequest;
import com.swp391.skincare_products_sales_system.dto.request.QuestionRequest;
import com.swp391.skincare_products_sales_system.dto.request.QuizCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.QuizResponse;
import com.swp391.skincare_products_sales_system.entity.Answer;
import com.swp391.skincare_products_sales_system.entity.Question;
import com.swp391.skincare_products_sales_system.entity.Quiz;
import com.swp391.skincare_products_sales_system.repository.AnswerRepository;
import com.swp391.skincare_products_sales_system.repository.QuestionRepository;
import com.swp391.skincare_products_sales_system.repository.QuizRepository;
import com.swp391.skincare_products_sales_system.service.QuizService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuizServiceImpl implements QuizService {
    QuizRepository quizRepository;
    QuestionRepository questionRepository;
    AnswerRepository answerRepository;

    @Override
    @Transactional
    public QuizResponse createQuiz(QuizCreationRequest quizRequest) {
        Quiz quiz = Quiz.builder()
                .title(quizRequest.getTitle())
                .description(quizRequest.getDescription())
                .build();
        for (QuestionRequest questionRequest : quizRequest.getQuestions()) {
            Question question = Question.builder()
                    .title(questionRequest.getTitle())
                    .quiz(quiz)
                    .build();
            questionRepository.save(question);
            for(AnswerRequest answerRequest : questionRequest.getAnswers()){
                Answer answer = Answer.builder()
                        .answerText(answerRequest.getAnswerText())
                        .skinType(answerRequest.getSkinType())
                        .question(question)
                        .build();
                answerRepository.save(answer);
            }
        }
        quizRepository.save(quiz);
        return QuizResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .build();
    }
}
