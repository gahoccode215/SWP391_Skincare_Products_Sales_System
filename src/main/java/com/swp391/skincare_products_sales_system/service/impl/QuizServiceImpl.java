package com.swp391.skincare_products_sales_system.service.impl;

import com.swp391.skincare_products_sales_system.dto.request.*;
import com.swp391.skincare_products_sales_system.dto.response.QuizResponse;
import com.swp391.skincare_products_sales_system.entity.Answer;
import com.swp391.skincare_products_sales_system.entity.Question;
import com.swp391.skincare_products_sales_system.entity.Quiz;
import com.swp391.skincare_products_sales_system.entity.Result;
import com.swp391.skincare_products_sales_system.enums.ErrorCode;
import com.swp391.skincare_products_sales_system.enums.SkinType;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Quiz createQuiz(QuizCreationRequest quizRequest) {
        Quiz quiz = Quiz.builder()
                .title(quizRequest.getTitle())
                .description(quizRequest.getDescription())
                .status(Status.ACTIVE)
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
        return quizRepository.save(quiz);
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
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.QUIZ_NOT_FOUND));
    }

    @Override
    @Transactional
    public void updateQuiz(QuizUpdateRequest request, Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.QUIZ_NOT_FOUND));
        log.info("VAO DUOC DAY");
        if(request.getTitle() != null) quiz.setTitle(request.getTitle());
        if(request.getDescription() != null) quiz.setDescription(request.getDescription());
        if(request.getStatus() != null) quiz.setStatus(request.getStatus());

        for(QuestionRequest questionRequest : request.getQuestions()){
            Question question = questionRepository.findById(questionRequest.getQuestionId()).orElseThrow(() -> new AppException(ErrorCode.QUESTION_NOT_FOUND));
            question.setTitle(questionRequest.getTitle());
            questionRepository.save(question);
            for(AnswerRequest answerRequest : questionRequest.getAnswers()){
                Answer answer = answerRepository.findById(answerRequest.getAnswerId())
                        .orElseThrow(() -> new AppException(ErrorCode.ANSWER_NOT_FOUND));
                answer.setAnswerText(answerRequest.getAnswerText());
                answer.setSkinType(answerRequest.getSkinType());
                answerRepository.save(answer);
            }
        }
        log.info("{}", quiz.getTitle());
        quizRepository.save(quiz);
        toQuizResponse(quiz);
    }

    @Override
    public List<QuizResponse> getAll(boolean admin) {
        List<Quiz> list;
        if (admin) {
            list = quizRepository.findAll();
        } else {
            list = quizRepository.findAll().stream().filter(quiz -> quiz.getStatus() == Status.ACTIVE).toList();
        }

        // Convert each Quiz to QuizResponse and include questions and results
        return list.stream()
                .map(this::toQuizResponse)
                .toList();
    }

    @Override
    public Result submitQuiz(SubmitQuiz submitQuiz, Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new AppException(ErrorCode.QUIZ_NOT_FOUND));
        Result resultEntity = new Result();

        Map<SkinType, Long> skinTypeCount = new HashMap<>();
        skinTypeCount.put(SkinType.DRY_SKIN, 0L);
        skinTypeCount.put(SkinType.SENSITIVE_SKIN, 0L);
        skinTypeCount.put(SkinType.OILY_SKIN, 0L);
        skinTypeCount.put(SkinType.NORMAL_SKIN, 0L);

        for (Question question : quiz.getQuestions()) {
            Long answerId = submitQuiz.getAnswers().get(question.getId());

            if (answerId != null) {
                Answer answer = answerRepository.findById(answerId)
                        .orElseThrow(() -> new AppException(ErrorCode.ANSWER_NOT_FOUND));

                SkinType skinType = answer.getSkinType();
//                log.info("{}", skinType);
                if (skinType != null) {
                    skinTypeCount.merge(skinType, 1L, Long::sum);
//                    log.info("{}", skinTypeCount);
                }
            }
        }

        SkinType result = skinTypeCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(SkinType.SENSITIVE_SKIN);

        if (skinTypeCount.get(SkinType.DRY_SKIN).equals(skinTypeCount.get(SkinType.OILY_SKIN))) {
            resultEntity.setSkinType(SkinType.COMBINATION_SKIN);
        }
        if (skinTypeCount.get(SkinType.DRY_SKIN).equals(skinTypeCount.get(SkinType.NORMAL_SKIN))) {
            resultEntity.setSkinType(SkinType.COMBINATION_SKIN);
        }
        if (skinTypeCount.get(SkinType.OILY_SKIN).equals(skinTypeCount.get(SkinType.NORMAL_SKIN))) {
            resultEntity.setSkinType(SkinType.COMBINATION_SKIN);
        }


//        log.info("{}", calculateQuizResult(quiz, submitQuiz.getAnswers()));
        // Tính toán kết quả
//        SkinType result = calculateQuizResult(quiz, submitQuiz.getAnswers());

        // Log kết quả trước khi gán
        log.info("Result before setting to resultEntity: {}", result);

        resultEntity.setSkinType(result);

        // Gán recommendation từ result
        resultEntity.setRecommendation(result.getRecommendation());

        // Log kết quả sau khi gán
        log.info("ResultEntity after setting: {}", resultEntity);

        return resultEntity;
    }

    private QuizResponse toQuizResponse(Quiz quiz) {
        return QuizResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .status(quiz.getStatus())  // Chuyển đổi status thành chuỗi
                .questions(quiz.getQuestions())  // Thêm danh sách câu hỏi
                .results(quiz.getResults())  // Thêm danh sách kết quả
                .build();
    }
    private SkinType calculateQuizResult(Quiz quiz, Map<Long, Long> answers) {
//        log.info("{}", answers);
        Map<SkinType, Long> skinTypeCount = new HashMap<>();
        skinTypeCount.put(SkinType.DRY_SKIN, 0L);
        skinTypeCount.put(SkinType.SENSITIVE_SKIN, 0L);
        skinTypeCount.put(SkinType.OILY_SKIN, 0L);
        skinTypeCount.put(SkinType.NORMAL_SKIN, 0L);

        for (Question question : quiz.getQuestions()) {
            Long answerId = answers.get(question.getId());

            if (answerId != null) {
                Answer answer = answerRepository.findById(answerId)
                        .orElseThrow(() -> new AppException(ErrorCode.ANSWER_NOT_FOUND));

                SkinType skinType = answer.getSkinType();
//                log.info("{}", skinType);
                if (skinType != null) {
                    skinTypeCount.merge(skinType, 1L, Long::sum);
//                    log.info("{}", skinTypeCount);
                }
            }
        }

        SkinType result = skinTypeCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(SkinType.SENSITIVE_SKIN);
//        log.info("{}", result);

        if (skinTypeCount.get(SkinType.DRY_SKIN).equals(skinTypeCount.get(SkinType.OILY_SKIN))) {
            return SkinType.COMBINATION_SKIN;
        }
        if (skinTypeCount.get(SkinType.DRY_SKIN).equals(skinTypeCount.get(SkinType.NORMAL_SKIN))) {
            return SkinType.COMBINATION_SKIN;
        }
        if (skinTypeCount.get(SkinType.OILY_SKIN).equals(skinTypeCount.get(SkinType.NORMAL_SKIN))) {
            return SkinType.COMBINATION_SKIN;
        }
//        log.info("{}", result);
        return result;
    }

}
