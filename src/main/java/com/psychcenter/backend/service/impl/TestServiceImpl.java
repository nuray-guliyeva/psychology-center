package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.common.exception.base.ResourceNotFoundException;
import com.psychcenter.backend.common.exception.test.AnswerNotFoundException;
import com.psychcenter.backend.common.exception.test.QuestionNotFoundException;
import com.psychcenter.backend.common.exception.test.TestNotFoundException;
import com.psychcenter.backend.dto.request.AnswerCreateRequestDto;
import com.psychcenter.backend.dto.request.QuestionCreateRequestDto;
import com.psychcenter.backend.dto.request.SubmitTestRequestDto;
import com.psychcenter.backend.dto.request.TestCreateRequestDto;
import com.psychcenter.backend.dto.response.AnswerResponseDto;
import com.psychcenter.backend.dto.response.QuestionResponseDto;
import com.psychcenter.backend.dto.response.TestResponseDto;
import com.psychcenter.backend.dto.response.TestResultResponseDto;
import com.psychcenter.backend.model.entity.*;
import com.psychcenter.backend.model.enums.TestLevel;
import com.psychcenter.backend.repository.*;
import com.psychcenter.backend.security.util.SecurityUtils;
import com.psychcenter.backend.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final AnswerOptionRepository answerOptionRepository;
    private final UserTestResultRepository userTestResultRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public TestResponseDto createTest(TestCreateRequestDto testCreateRequest) {

        Test test = Test.builder()
                .name(testCreateRequest.getName())
                .description(testCreateRequest.getDescription())
                .build();

        Test saved = testRepository.save(test);

        return TestResponseDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .description(saved.getDescription())
                .build();
    }

    @Override
    @Transactional
    public QuestionResponseDto addQuestion(Long testId, QuestionCreateRequestDto questionCreateRequest) {

        Test test = testRepository.findById(testId)
                .orElseThrow(() ->
                        new TestNotFoundException("Test not found")
                );

        Question q = Question.builder()
                .text(questionCreateRequest.getText())
                .test(test)
                .build();

        Question saved = questionRepository.save(q);

        return QuestionResponseDto.builder()
                .id(saved.getId())
                .text(saved.getText())
                .build();
    }

    @Override
    @Transactional
    public AnswerResponseDto addAnswer(Long questionId, AnswerCreateRequestDto answerCreateRequest) {

        Question q = questionRepository.findById(questionId)
                .orElseThrow(() ->
                        new QuestionNotFoundException("Question not found")
                );

        AnswerOption a = AnswerOption.builder()
                .text(answerCreateRequest.getText())
                .score(answerCreateRequest.getScore())
                .question(q)
                .build();

        AnswerOption saved = answerOptionRepository.save(a);

        return AnswerResponseDto.builder()
                .id(saved.getId())
                .text(saved.getText())
                .score(saved.getScore())
                .build();
    }

    @Override
    @Transactional
    public TestResultResponseDto submitTest(SubmitTestRequestDto submitTestRequest) {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        Test test = testRepository.findById(submitTestRequest.getTestId())
                .orElseThrow(() ->
                        new TestNotFoundException("Test not found")
                );

        int totalScore = 0;

        for (var entry : submitTestRequest.getAnswers().entrySet()) {

            Long questionId = entry.getKey();
            Long answerId = entry.getValue();

            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() ->
                            new QuestionNotFoundException("Question not found")
                    );

            AnswerOption answer = answerOptionRepository.findById(answerId)
                    .orElseThrow(() ->
                            new AnswerNotFoundException("Answer not found")
                    );

            if (!answer.getQuestion().getId().equals(question.getId())) {
                throw new IllegalArgumentException(
                        "Answer does not belong to question"
                );
            }

            totalScore += answer.getScore();
        }

        TestLevel level;

        if (totalScore < 10) {
            level = TestLevel.LOW;
        } else if (totalScore < 20) {
            level = TestLevel.MEDIUM;
        } else {
            level = TestLevel.HIGH;
        }

        UserTestResult result = UserTestResult.builder()
                .totalScore(totalScore)
                .level(level)
                .test(test)
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        userTestResultRepository.save(result);

        return TestResultResponseDto.builder()
                .testName(test.getName())
                .score(totalScore)
                .level(level)
                .date(result.getCreatedAt())
                .build();
    }

    @Override
    public List<TestResultResponseDto> getCurrentUserResults() {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        return userTestResultRepository.findByUserId(user.getId())
                .stream()
                .map(r -> TestResultResponseDto.builder()
                        .testName(r.getTest().getName())
                        .score(r.getTotalScore())
                        .level(r.getLevel())
                        .date(r.getCreatedAt())
                        .build()
                )
                .toList();
    }

}