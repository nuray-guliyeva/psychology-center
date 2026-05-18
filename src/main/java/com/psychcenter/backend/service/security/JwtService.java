package com.psychcenter.backend.service.security;

// JWT library imports
import io.jsonwebtoken.*;

// Utility class for creating secure signing keys
import io.jsonwebtoken.security.Keys;

// Injects values from application.properties
import org.springframework.beans.factory.annotation.Value;

// Marks this class as Spring service
import org.springframework.stereotype.Service;

// Used for UTF-8 string conversion
import java.nio.charset.StandardCharsets;

// Represents cryptographic signing key
import java.security.Key;

// Used for token creation and expiration dates
import java.util.Date;

// Registers this class as Spring service bean
@Service
public class JwtService {

    // Reads JWT secret key from application.properties
    // Example:
    // jwt.secret=mysecretkey
    @Value("${jwt.secret}")
    private String SECRET;

    // Creates signing key from secret string
    private Key getSignKey() {

        // Converts secret string into secure HMAC SHA key
        return Keys.hmacShaKeyFor(

                // Converts string into UTF-8 bytes
                SECRET.getBytes(StandardCharsets.UTF_8)
        );
    }

    // Generates JWT token
    public String generateToken(String email, String role) {

        // Builds JWT token
        return Jwts.builder()

                // Sets token subject (usually username/email)
                .setSubject(email)

                // Adds custom claim "role"
                .claim("role", "ROLE_" + role)

                // Sets token creation date
                .setIssuedAt(new Date())

                // Sets expiration date
                // Current time + 24 hours
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60 * 24
                        )
                )

                // Signs token using secret key and algorithm
                .signWith(
                        getSignKey(),
                        SignatureAlgorithm.HS256
                )

                // Converts JWT object into string
                .compact();
    }

    // Extracts username/email from token
    public String extractUsername(String token) {

        // Gets subject from claims
        return extractAllClaims(token).getSubject();
    }

    // Extracts all JWT claims from token
    private Claims extractAllClaims(String token) {

        // Creates JWT parser
        return Jwts.parserBuilder()

                // Sets signing key for validation
                .setSigningKey(getSignKey())

                // Builds parser
                .build()

                // Parses JWT token
                .parseClaimsJws(token)

                // Returns token body/claims
                .getBody();
    }

    // Validates token
    public boolean isTokenValid(String token, String username) {

        // Extracts username from token
        final String extracted =
                extractUsername(token);

        // Returns true if:
        // 1. usernames match
        // 2. token is not expired
        return extracted.equals(username)
                && !isTokenExpired(token);
    }

    // Checks if token is expired
    private boolean isTokenExpired(String token) {

        // Compares expiration date with current date
        return extractAllClaims(token)
                .getExpiration()
                .before(new java.util.Date());
    }
}