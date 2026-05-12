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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

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

        var refresh = refreshTokenService.create(user);

        return AuthResponse.builder()
                .accessToken(jwtService.generateToken(user.getEmail(), user.getRole().name()))
                .refreshToken(refresh.getToken())
                .role(user.getRole().name())
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

        var refresh = refreshTokenService.create(user);

        return AuthResponse.builder()
                .accessToken(jwtService.generateToken(user.getEmail(), user.getRole().name()))
                .refreshToken(refresh.getToken())
                .role(user.getRole().name())
                .build();
    }
}