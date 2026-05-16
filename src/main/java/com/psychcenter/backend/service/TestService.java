package com.psychcenter.backend.service;

import com.psychcenter.backend.dto.request.AnswerCreateRequestDto;
import com.psychcenter.backend.dto.request.QuestionCreateRequestDto;
import com.psychcenter.backend.dto.request.SubmitTestRequestDto;
import com.psychcenter.backend.dto.request.TestCreateRequestDto;
import com.psychcenter.backend.dto.response.*;

import java.util.List;

public interface TestService {

    TestResponseDto createTest(TestCreateRequestDto testCreateRequest);

    QuestionResponseDto addQuestion(Long testId, QuestionCreateRequestDto questionCreateRequest);

    AnswerResponseDto addAnswer(Long questionId, AnswerCreateRequestDto answerCreateRequest);

    TestResultResponseDto submitTest(SubmitTestRequestDto submitTestRequest);

    List<TestResultResponseDto> getTestResult(Long userId);
}