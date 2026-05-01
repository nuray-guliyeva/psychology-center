package com.psychcenter.backend.service.security;

import com.psychcenter.backend.dto.security.LoginRequest;
import com.psychcenter.backend.dto.security.RegisterRequest;
import com.psychcenter.backend.dto.security.AuthResponse;
import com.psychcenter.backend.model.entity.User;
import com.psychcenter.backend.model.enums.Role;
import com.psychcenter.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new com.psychcenter.backend.common.exception.auth.UserAlreadyExistsException(
                    "User already exists"
            );
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.generateToken(user.getEmail(), user.getRole().name()))
                .build();
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new com.psychcenter.backend.common.exception.auth.InvalidCredentialsException(
                                "Invalid email or password"
                        )
                );

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new com.psychcenter.backend.common.exception.auth.InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        return AuthResponse.builder()
                .token(jwtService.generateToken(user.getEmail(), user.getRole().name()))
                .build();
    }
}