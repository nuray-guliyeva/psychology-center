package com.psychcenter.backend.booking.dto;

import com.psychcenter.backend.booking.entity.BookingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class BookingResponseDto {

    private Long id;

    private String psychologistName;

    private LocalDate date;

    private LocalTime time;

    private BookingStatus status;
}