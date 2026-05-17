package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.common.exception.base.ResourceNotFoundException;
import com.psychcenter.backend.common.exception.booking.BookingConflictException;
import com.psychcenter.backend.dto.request.BookingRequestDto;
import com.psychcenter.backend.dto.response.BookingResponseDto;
import com.psychcenter.backend.dto.response.CalendarDayDto;
import com.psychcenter.backend.mapper.BookingMapper;
import com.psychcenter.backend.model.entity.Booking;
import com.psychcenter.backend.model.entity.Psychologist;
import com.psychcenter.backend.model.entity.User;
import com.psychcenter.backend.model.enums.BookingStatus;
import com.psychcenter.backend.repository.BookingRepository;
import com.psychcenter.backend.repository.PsychologistRepository;
import com.psychcenter.backend.repository.UserRepository;
import com.psychcenter.backend.security.util.SecurityUtils;
import com.psychcenter.backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final PsychologistRepository psychologistRepository;
    private final BookingMapper bookingMapper;

    @Override
    @Transactional
    public BookingResponseDto create(BookingRequestDto dto) {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        Psychologist psychologist = psychologistRepository.findById(dto.getPsychologistId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Psychologist not found")
                );

        Booking booking = Booking.builder()
                .user(user)
                .psychologist(psychologist)
                .date(dto.getDate())
                .time(dto.getTime())
                .amount(50.0)
                .status(BookingStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        try {

            Booking savedBooking = bookingRepository.saveAndFlush(booking);

            return BookingResponseDto.builder()
                    .id(savedBooking.getId())
                    .psychologistName(psychologist.getFirstName())
                    .date(savedBooking.getDate())
                    .time(savedBooking.getTime())
                    .status(savedBooking.getStatus())
                    .build();

        } catch (DataIntegrityViolationException ex) {

            throw new BookingConflictException(
                    "This time slot is already booked"
            );
        }
    }


    @Override
    public List<LocalTime> getAvailableSlots(Long psychologistId, LocalDate date) {

        List<LocalTime> allSlots = List.of(
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                LocalTime.of(16, 0)
        );

        List<LocalTime> bookedSlots = bookingRepository
                .findByPsychologistIdAndDate(psychologistId, date)
                .stream()
                .map(Booking::getTime)
                .toList();

        return allSlots.stream()
                .filter(slot -> !bookedSlots.contains(slot))
                .toList();
    }

    @Override
    public List<CalendarDayDto> getCalendar(Long psychologistId) {

        return bookingRepository.findByPsychologistId(psychologistId)
                .stream()
                .collect(Collectors.groupingBy(Booking::getDate))
                .entrySet()
                .stream()
                .map(entry -> CalendarDayDto.builder()
                        .date(entry.getKey())
                        .bookings(entry.getValue()
                                .stream()
                                .sorted(Comparator.comparing(Booking::getTime))
                                .map(bookingMapper::toDto)
                                .toList()
                        )
                        .totalCount(entry.getValue().size())
                        .build()
                )
                .sorted(Comparator.comparing(CalendarDayDto::getDate))
                .toList();
    }

}