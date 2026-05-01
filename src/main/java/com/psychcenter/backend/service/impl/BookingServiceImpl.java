package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.dto.request.BookingRequestDto;
import com.psychcenter.backend.dto.response.BookingResponseDto;
import com.psychcenter.backend.model.entity.Booking;
import com.psychcenter.backend.model.entity.Psychologist;
import com.psychcenter.backend.model.entity.User;
import com.psychcenter.backend.model.enums.BookingStatus;
import com.psychcenter.backend.repository.BookingRepository;
import com.psychcenter.backend.repository.PsychologistRepository;
import com.psychcenter.backend.repository.UserRepository;
import com.psychcenter.backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final PsychologistRepository psychologistRepository;

    @Override
    public BookingResponseDto create(BookingRequestDto dto, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Psychologist psychologist = psychologistRepository.findById(dto.getPsychologistId())
                .orElseThrow();

        if (bookingRepository.existsByPsychologistAndDateAndTime(
                psychologist,
                dto.getDate(),
                dto.getTime()
        )) {
            throw new com.psychcenter.backend.common.exception.booking.BookingConflictException(
                    "This time slot is already booked"
            );
        }

        Booking booking = Booking.builder()
                .user(user)
                .psychologist(psychologist)
                .date(dto.getDate())
                .time(dto.getTime())
                .status(BookingStatus.PENDING)
                .build();

        bookingRepository.save(booking);

        return BookingResponseDto.builder()
                .id(booking.getId())
                .psychologistName(psychologist.getFirstName())
                .date(booking.getDate())
                .time(booking.getTime())
                .status(booking.getStatus())
                .build();
    }
}