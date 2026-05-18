package com.psychcenter.backend.service.security;

// DTO containing login request data
import com.psychcenter.backend.dto.security.LoginRequest;

// DTO containing registration request data
import com.psychcenter.backend.dto.security.RegisterRequest;

// DTO returned after successful authentication
import com.psychcenter.backend.dto.security.AuthResponse;

// User entity
import com.psychcenter.backend.model.entity.User;

// Enum containing system roles
import com.psychcenter.backend.model.enums.Role;

// Repository for database operations with users
import com.psychcenter.backend.repository.UserRepository;

// Lombok annotation for automatic constructor generation
import lombok.RequiredArgsConstructor;

// Used for password hashing and verification
import org.springframework.security.crypto.password.PasswordEncoder;

// Marks this class as Spring service
import org.springframework.stereotype.Service;

// Makes all methods transactional
// Database changes rollback automatically if error happens
import org.springframework.transaction.annotation.Transactional;

// Registers this class as Spring service bean
@Service

// Automatically creates constructor for final fields
@RequiredArgsConstructor

// All methods work inside database transaction
@Transactional
public class AuthService {

    // Repository for user database operations
    private final UserRepository userRepository;

    // Service for generating and validating JWT tokens
    private final JwtService jwtService;

    // Password encoder for hashing passwords
    private final PasswordEncoder passwordEncoder;

    // Service for refresh token operations
    private final RefreshTokenService refreshTokenService;

    // Method for user registration
    public AuthResponse register(RegisterRequest request) {

        // Checks if user with this email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {

            // Throws custom exception if email already exists
            throw new com.psychcenter.backend.common.exception.auth.UserAlreadyExistsException(
                    "User already exists"
            );
        }

        // Creates new User object using builder pattern
        User user = User.builder()

                // Sets user's name
                .name(request.getName())

                // Sets user's email
                .email(request.getEmail())

                // Encodes password before saving
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )

                // Sets default role USER
                .role(Role.USER)

                // Builds final User object
                .build();

        // Saves user into database
        userRepository.save(user);

        // Creates refresh token for user
        var refresh = refreshTokenService.create(user);

        // Returns authentication response
        return AuthResponse.builder()

                // Generates JWT access token
                .accessToken(
                        jwtService.generateToken(
                                user.getEmail(),
                                user.getRole().name()
                        )
                )

                // Adds refresh token
                .refreshToken(refresh.getToken())

                // Adds user role
                .role(user.getRole().name())

                // Builds final response
                .build();
    }

    // Method for user login
    public AuthResponse login(LoginRequest request) {

        // Searches user by email
        User user = userRepository.findByEmail(request.getEmail())

                // Throws exception if user not found
                .orElseThrow(() ->
                        new com.psychcenter.backend.common.exception.auth.InvalidCredentialsException(
                                "Invalid email or password"
                        )
                );

        // Checks if password matches encoded password
        if (
                !passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                )
        ) {

            // Throws exception if password is incorrect
            throw new com.psychcenter.backend.common.exception.auth.InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        // Creates refresh token for logged-in user
        var refresh = refreshTokenService.create(user);

        // Returns authentication response
        return AuthResponse.builder()

                // Generates JWT access token
                .accessToken(
                        jwtService.generateToken(
                                user.getEmail(),
                                user.getRole().name()
                        )
                )

                // Adds refresh token
                .refreshToken(refresh.getToken())

                // Adds user role
                .role(user.getRole().name())

                // Builds final response
                .build();
    }
}