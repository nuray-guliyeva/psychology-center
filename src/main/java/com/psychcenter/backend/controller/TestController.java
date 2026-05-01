package com.psychcenter.backend.controller;

import com.psychcenter.backend.model.entity.TestResult;
import com.psychcenter.backend.service.TestService;
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