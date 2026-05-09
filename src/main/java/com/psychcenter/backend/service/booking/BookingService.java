package com.psychcenter.backend.service.booking;

import com.psychcenter.backend.dto.request.BookingRequestDto;
import com.psychcenter.backend.dto.response.BookingResponseDto;
import com.psychcenter.backend.dto.response.CalendarDayDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingService {

    BookingResponseDto create(BookingRequestDto dto);

    List<LocalTime> getAvailableSlots(Long psychologistId, LocalDate date);

    List<CalendarDayDto> getCalendar(Long psychologistId);

}