package com.psychcenter.backend.service.analytics.impl;

import com.psychcenter.backend.dto.response.StatsDto;
import com.psychcenter.backend.repository.booking.BookingRepository;
import com.psychcenter.backend.repository.psychologist.PsychologistRepository;
import com.psychcenter.backend.repository.user.UserRepository;
import com.psychcenter.backend.service.analytics.AnalyticsService;
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