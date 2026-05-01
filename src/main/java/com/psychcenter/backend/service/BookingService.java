package com.psychcenter.backend.service;

import com.psychcenter.backend.dto.request.BookingRequestDto;
import com.psychcenter.backend.dto.response.BookingResponseDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface BookingService {

    BookingResponseDto create(BookingRequestDto dto, String email);

    List<LocalTime> getAvailableSlots(Long psychologistId, LocalDate date);

    Map<LocalDate, List<BookingResponseDto>> getCalendar(Long psychologistId);

}
