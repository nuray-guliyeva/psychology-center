package com.psychcenter.backend.service;

import com.psychcenter.backend.dto.request.TestSubmitRequest;
import com.psychcenter.backend.dto.response.TestResultResponseDto;
import com.psychcenter.backend.model.entity.TestResult;

public interface TestService {

    TestResult calculate(String testName, int score);

    TestResultResponseDto submit(TestSubmitRequest request, String email);

}