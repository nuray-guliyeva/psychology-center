package com.psychcenter.backend.service.security;

import com.psychcenter.backend.model.entity.RefreshToken;
import com.psychcenter.backend.model.entity.User;
import com.psychcenter.backend.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    public RefreshToken create(User user) {
        RefreshToken token = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusDays(7))
                .user(user)
                .build();

        return repository.save(token);
    }

    public RefreshToken validate(String token) {
        RefreshToken rt = repository.findByToken(token)
                .orElseThrow();

        if (rt.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        return rt;
    }
}