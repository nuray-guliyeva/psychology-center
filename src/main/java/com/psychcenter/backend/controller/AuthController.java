package com.psychcenter.backend.controller;

import com.psychcenter.backend.dto.security.AuthResponse;
import com.psychcenter.backend.dto.security.LoginRequest;
import com.psychcenter.backend.dto.security.RegisterRequest;
import com.psychcenter.backend.service.security.AuthService;
import com.psychcenter.backend.service.security.JwtService;
import com.psychcenter.backend.service.security.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestParam String refreshToken) {

        var rt = refreshTokenService.validate(refreshToken);

        String newAccess = jwtService.generateToken(
                rt.getUser().getEmail(),
                rt.getUser().getRole().name()
        );

        return AuthResponse.builder()
                .accessToken(newAccess)
                .refreshToken(refreshToken)
                .role(rt.getUser().getRole().name())
                .build();
    }
}