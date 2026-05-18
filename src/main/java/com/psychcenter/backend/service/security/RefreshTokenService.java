package com.psychcenter.backend.service.security;

// Custom exception thrown when refresh token is expired
import com.psychcenter.backend.common.exception.auth.RefreshTokenExpiredException;

// Custom exception thrown when resource is not found
import com.psychcenter.backend.common.exception.base.ResourceNotFoundException;

// RefreshToken entity
import com.psychcenter.backend.model.entity.RefreshToken;

// User entity
import com.psychcenter.backend.model.entity.User;

// Repository for refresh token database operations
import com.psychcenter.backend.repository.RefreshTokenRepository;

// Lombok annotation for automatic constructor generation
import lombok.RequiredArgsConstructor;

// Marks this class as Spring service
import org.springframework.stereotype.Service;

// Used for working with date and time
import java.time.LocalDateTime;

// Used for generating unique random tokens
import java.util.UUID;

// Registers this class as Spring service bean
@Service

// Automatically creates constructor for final fields
@RequiredArgsConstructor
public class RefreshTokenService {

    // Repository for refresh token operations
    private final RefreshTokenRepository repository;

    // Creates new refresh token for user
    public RefreshToken create(User user) {

        // Deletes old refresh tokens for this user
        // So user will only have one active refresh token
        repository.deleteByUser(user);

        // Creates new RefreshToken object
        RefreshToken token = RefreshToken.builder()

                // Generates random unique token string
                .token(UUID.randomUUID().toString())

                // Sets expiration date to 7 days from now
                .expiryDate(
                        LocalDateTime.now().plusDays(7)
                )

                // Connects token with user
                .user(user)

                // Builds final object
                .build();

        // Saves token into database and returns it
        return repository.save(token);
    }

    // Validates refresh token
    public RefreshToken validate(String token) {

        // Searches refresh token in database
        RefreshToken rt = repository.findByToken(token)

                // Throws exception if token not found
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Refresh token not found"
                        )
                );

        // Checks if token expiration date is before current time
        if (
                rt.getExpiryDate()
                        .isBefore(LocalDateTime.now())
        ) {

            // Throws exception if token expired
            throw new RefreshTokenExpiredException(
                    "Refresh token expired"
            );
        }

        // Returns valid refresh token
        return rt;
    }
}