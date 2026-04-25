package com.psychcenter.backend.auth.controller;

import com.psychcenter.backend.auth.dto.*;
import com.psychcenter.backend.auth.service.AuthService;
import com.psychcenter.backend.common.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ApiResponse.success(
                service.register(request),
                "User registered successfully"
        );
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success(
                service.login(request),
                "Login successful"
        );
    }
}