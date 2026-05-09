package com.psychcenter.backend.controller.test;

import com.psychcenter.backend.model.entity.test.TestResult;
import com.psychcenter.backend.service.test.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService service;

    @PostMapping("/calculate")
    public TestResult calculate(
            @RequestParam String name,
            @RequestParam int score
    ) {
        return service.calculate(name, score);
    }
}