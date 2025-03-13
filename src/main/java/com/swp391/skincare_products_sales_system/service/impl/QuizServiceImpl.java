package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.dto.request.AnswerRequest;
import com.swp391.skincare_products_sales_system.dto.request.QuestionRequest;
import com.swp391.skincare_products_sales_system.dto.request.QuizCreationRequest;
import com.swp391.skincare_products_sales_system.dto.response.QuizResponse;
import com.swp391.skincare_products_sales_system.entity.Answer;
import com.swp391.skincare_products_sales_system.entity.Question;
import com.swp391.skincare_products_sales_system.entity.Quiz;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.enums.Status;
import com.swp391.skincare_products_sales_system.exception.AppException;
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
        return toQuizResponse(quiz);
    }

    @Override
    @Transactional
    public void deleteQuiz(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.QUIZ_NOT_FOUND));
        quizRepository.delete(quiz);
    }

    @Override
    @Transactional
    public void changeStatusQuiz(Long id, Status status) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.QUIZ_NOT_FOUND));
        quiz.setStatus(status);
        quizRepository.save(quiz);
    }

    @Override
    public QuizResponse getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.QUIZ_NOT_FOUND));
        return toQuizResponse(quiz);
    }

    private QuizResponse toQuizResponse(Quiz quiz){
        return QuizResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .status(Status.ACTIVE)
                .description(quiz.getDescription())
                .build();
    }
}
