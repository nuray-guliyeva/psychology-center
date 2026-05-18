package com.psychcenter.backend.service.security.impl;

// Imports User entity from project
import com.psychcenter.backend.model.entity.User;

// Repository used for database operations with User entity
import com.psychcenter.backend.repository.UserRepository;

// Lombok annotation for automatic constructor generation
import lombok.RequiredArgsConstructor;

// Spring Security interface for loading user data
import org.springframework.security.core.userdetails.*;

// Represents user roles/authorities
import org.springframework.security.core.authority.SimpleGrantedAuthority;

// Marks this class as Spring service
import org.springframework.stereotype.Service;

// Utility class for working with lists
import java.util.List;

// Registers this class as Spring service bean
@Service

// Automatically creates constructor for final fields
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    // Repository used for finding users in database
    private final UserRepository userRepository;

    // Method automatically used by Spring Security
    // for loading user during authentication
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        // Searches user by email in database
        User user = userRepository.findByEmail(email)

                // Throws exception if user not found
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found"
                        )
                );

        // Returns Spring Security User object
        return new org.springframework.security.core.userdetails.User(

                // Username (email)
                user.getEmail(),

                // Encoded password
                user.getPassword(),

                // List of user roles/authorities
                List.of(

                        // Adds ROLE_ prefix required by Spring Security
                        new SimpleGrantedAuthority(
                                "ROLE_" + user.getRole().name()
                        )
                )
        );
    }
}