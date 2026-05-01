package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.dto.request.BookingRequestDto;
import com.psychcenter.backend.dto.response.BookingResponseDto;
import com.psychcenter.backend.mapper.BookingMapper;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final PsychologistRepository psychologistRepository;
    private final BookingMapper mapper;

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

        var booked = bookingRepository
                .findByPsychologistIdAndDate(psychologistId, date)
                .stream()
                .map(Booking::getTime)
                .toList();

        return allSlots.stream()
                .filter(slot -> !booked.contains(slot))
                .toList();
    }

    @Override
    public Map<LocalDate, List<BookingResponseDto>> getCalendar(Long psychologistId) {

        return bookingRepository.findAll()
                .stream()
                .filter(b -> b.getPsychologist().getId().equals(psychologistId))
                .collect(java.util.stream.Collectors.groupingBy(
                        Booking::getDate,
                        java.util.stream.Collectors.mapping(
                                mapper::toDto,
                                java.util.stream.Collectors.toList()
                        )
                ));
    }

}