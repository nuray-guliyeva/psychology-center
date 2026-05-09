package com.psychcenter.backend.service.test;

import com.psychcenter.backend.dto.request.TestSubmitRequest;
import com.psychcenter.backend.dto.response.TestResultResponseDto;
import com.psychcenter.backend.model.entity.test.TestResult;

public interface TestService {

    TestResult calculate(String testName, int score);

    TestResultResponseDto submit(TestSubmitRequest request, String email);

}