package com.psychcenter.backend.booking.controller;

import com.psychcenter.backend.booking.dto.BookingRequestDto;
import com.psychcenter.backend.booking.dto.BookingResponseDto;
import com.psychcenter.backend.booking.service.BookingService;
import com.psychcenter.backend.common.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @PostMapping
    public ApiResponse<BookingResponseDto> create(
            @Valid @RequestBody BookingRequestDto dto,
            Authentication authentication
    ) {
        String email = authentication.getName();

        return ApiResponse.success(
                service.create(dto, email),
                "Booking created successfully"
        );
    }
}