package com.psychcenter.backend.controller;

import com.psychcenter.backend.dto.request.AnswerCreateRequestDto;
import com.psychcenter.backend.dto.request.QuestionCreateRequestDto;
import com.psychcenter.backend.dto.request.SubmitTestRequestDto;
import com.psychcenter.backend.dto.request.TestCreateRequestDto;
import com.psychcenter.backend.dto.response.AnswerResponseDto;
import com.psychcenter.backend.dto.response.QuestionResponseDto;
import com.psychcenter.backend.dto.response.TestResponseDto;
import com.psychcenter.backend.dto.response.TestResultResponseDto;
import com.psychcenter.backend.service.TestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService service;

    @PostMapping
    public TestResponseDto createTest(@Valid
                                      @RequestBody TestCreateRequestDto testCreateRequestDto) {
        return service.createTest(testCreateRequestDto);
    }

    @PostMapping("/{testId}/questions")
    public QuestionResponseDto addQuestion(
            @Valid
            @PathVariable Long testId,
            @RequestBody QuestionCreateRequestDto questionCreateRequestDto
    ) {
        return service.addQuestion(testId, questionCreateRequestDto);
    }

    @PostMapping("/questions/{questionId}/answers")
    public AnswerResponseDto addAnswer(
            @Valid
            @PathVariable Long questionId,
            @RequestBody AnswerCreateRequestDto answerCreateRequestDto
    ) {
        return service.addAnswer(questionId, answerCreateRequestDto);
    }

    @PostMapping("/submit")
    public TestResultResponseDto submit(@Valid
                                        @RequestBody SubmitTestRequestDto submitTestRequestDto) {
        return service.submitTest(submitTestRequestDto);
    }

    @GetMapping("/history")
    public List<TestResultResponseDto> history() {
        return service.getCurrentUserResults();
    }
}