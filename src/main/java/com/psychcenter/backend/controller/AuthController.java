package com.psychcenter.backend.controller;

import com.psychcenter.backend.dto.security.AuthResponse;
import com.psychcenter.backend.dto.security.LoginRequest;
import com.psychcenter.backend.dto.security.RegisterRequest;
import com.psychcenter.backend.service.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}