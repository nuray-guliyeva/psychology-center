package com.psychcenter.backend.controller;

import com.psychcenter.backend.common.api.ApiResponse;
import com.psychcenter.backend.dto.request.BookingRequestDto;
import com.psychcenter.backend.dto.response.BookingResponseDto;
import com.psychcenter.backend.dto.response.CalendarDayDto;
import com.psychcenter.backend.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/slots")
    public ApiResponse<List<LocalTime>> getSlots(
            @RequestParam Long psychologistId,
            @RequestParam LocalDate date
    ) {
        return ApiResponse.success(
                bookingService.getAvailableSlots(psychologistId, date),
                "Available slots fetched"
        );
    }

    @GetMapping("/calendar")
    public ApiResponse<List<CalendarDayDto>> calendar(
            @RequestParam Long psychologistId
    ) {
        return ApiResponse.success(
                bookingService.getCalendar(psychologistId),
                "Calendar fetched"
        );
    }

    @PostMapping
    public ApiResponse<BookingResponseDto> create(
            @RequestBody @Valid BookingRequestDto dto
    ) {
        return ApiResponse.success(
                bookingService.create(dto),
                "Booking created"
        );
    }
}