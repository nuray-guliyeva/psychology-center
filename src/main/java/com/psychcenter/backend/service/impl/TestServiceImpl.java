package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.dto.request.TestSubmitRequest;
import com.psychcenter.backend.dto.response.TestResultResponseDto;
import com.psychcenter.backend.model.entity.TestResult;
import com.psychcenter.backend.model.enums.TestLevel;
import com.psychcenter.backend.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public TestResult calculate(String testName, int score) {

        TestLevel testLevel;

        if (score < 10) {
            testLevel = TestLevel.LOW;
        } else if (score < 20) {
            testLevel = TestLevel.MEDIUM;
        } else {
            testLevel = TestLevel.HIGH;
        }

        return TestResult.builder()
                .testName(testName)
                .score(score)
                .level(testLevel)
                .build();
    }

    @Override
    public TestResultResponseDto submit(TestSubmitRequest request, String email) {

        TestResult result = calculate(
                request.getTestName(),
                request.getScore()
        );

        return TestResultResponseDto.builder()
                .testName(result.getTestName())
                .score(result.getScore())
                .level(result.getLevel())
                .build();
    }
}