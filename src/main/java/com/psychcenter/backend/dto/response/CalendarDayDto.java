package com.psychcenter.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CalendarDayDto {

    private LocalDate date;
    private List<BookingResponseDto> bookings;
    private int totalCount;
}