package com.psychcenter.backend.booking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingRequestDto {

    @NotNull
    private Long psychologistId;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime time;
}