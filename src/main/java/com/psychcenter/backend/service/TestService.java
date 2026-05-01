package com.psychcenter.backend.service;

import com.psychcenter.backend.model.entity.TestResult;

public interface TestService {
    TestResult calculate(String testName, int score);
}