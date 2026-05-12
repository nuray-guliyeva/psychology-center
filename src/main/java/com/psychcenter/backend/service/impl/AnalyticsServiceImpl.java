package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.dto.response.StatsDto;
import com.psychcenter.backend.repository.BookingRepository;
import com.psychcenter.backend.repository.PsychologistRepository;
import com.psychcenter.backend.repository.UserRepository;
import com.psychcenter.backend.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final PsychologistRepository psychologistRepository;

    @Override
    public StatsDto stats() {
        return StatsDto.builder()
                .users(userRepository.count())
                .bookings(bookingRepository.count())
                .psychologists(psychologistRepository.count())
                .build();
    }

}