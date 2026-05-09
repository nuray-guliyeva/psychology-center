package com.psychcenter.backend.repository.security;

import com.psychcenter.backend.model.entity.security.RefreshToken;
import com.psychcenter.backend.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);

}