package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.model.entity.TestResult;
import com.psychcenter.backend.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public TestResult calculate(String testName, int score) {

        String level;

        if (score < 10) level = "LOW";
        else if (score < 20) level = "MEDIUM";
        else level = "HIGH";

        return TestResult.builder()
                .testName(testName)
                .score(score)
                .level(level)
                .build();
    }
}