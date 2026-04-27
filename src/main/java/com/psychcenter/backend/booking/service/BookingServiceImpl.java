package com.psychcenter.backend.booking.service;

import com.psychcenter.backend.booking.dto.BookingRequestDto;
import com.psychcenter.backend.booking.dto.BookingResponseDto;
import com.psychcenter.backend.booking.entity.Booking;
import com.psychcenter.backend.booking.entity.BookingStatus;
import com.psychcenter.backend.booking.mapper.BookingMapper;
import com.psychcenter.backend.booking.repository.BookingRepository;
import com.psychcenter.backend.common.exception.auth.InvalidCredentialsException;
import com.psychcenter.backend.common.exception.booking.BookingConflictException;
import com.psychcenter.backend.psychologist.entity.Psychologist;
import com.psychcenter.backend.psychologist.repository.PsychologistRepository;
import com.psychcenter.backend.user.entity.User;
import com.psychcenter.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final PsychologistRepository psychologistRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;

    @Override
    public BookingResponseDto create(BookingRequestDto dto, String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() ->
                        new com.psychcenter.backend.common.exception.psychologist.PsychologistNotFoundException(dto.getPsychologistId())
                );

        Psychologist psychologist = psychologistRepository.findById(dto.getPsychologistId())
                .orElseThrow(() ->
                        new RuntimeException("Psychologist not found") // дальше уберём
                );

        boolean exists = bookingRepository.existsByPsychologistAndDateAndTime(
                psychologist,
                dto.getDate(),
                dto.getTime()
        );

        if (exists) {
            throw new BookingConflictException("This time slot is already booked");
        }

        Booking booking = Booking.builder()
                .user(user)
                .psychologist(psychologist)
                .date(dto.getDate())
                .time(dto.getTime())
                .status(BookingStatus.PENDING)
                .build();

        Booking saved = bookingRepository.save(booking);

        return bookingMapper.toDto(saved);
    }
}