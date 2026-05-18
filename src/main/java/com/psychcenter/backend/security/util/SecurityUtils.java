package com.psychcenter.backend.security.util;

// Exception thrown when user has no permission
import org.springframework.security.access.AccessDeniedException;

// Represents anonymous (not logged in) authentication
import org.springframework.security.authentication.AnonymousAuthenticationToken;

// Exception thrown when authentication is missing
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

// Represents current authenticated user
import org.springframework.security.core.Authentication;

// Stores security information for current request
import org.springframework.security.core.context.SecurityContextHolder;

// Utility class for security-related helper methods
public class SecurityUtils {

    // Static method for getting current authenticated user's email
    public static String getCurrentUserEmail() {

        // Gets current authentication object from SecurityContext
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        // Checks if:
        // 1. authentication object is null
        // 2. user is not authenticated
        // 3. authentication is anonymous user
        if (
                authentication == null
                        || !authentication.isAuthenticated()
                        || authentication instanceof AnonymousAuthenticationToken
        ) {

            // Throws exception if user is not logged in
            throw new AuthenticationCredentialsNotFoundException(
                    "User is not authenticated"
            );
        }

        // Returns current authenticated username/email
        // Usually email is used as username
        return authentication.getName();
    }
}