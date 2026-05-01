package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.repository.*;
import com.psychcenter.backend.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final PsychologistRepository psychologistRepository;

    @Override
    public Map<String, Object> stats() {
        return Map.of(
                "users", userRepository.count(),
                "bookings", bookingRepository.count(),
                "psychologists", psychologistRepository.count()
        );
    }
}