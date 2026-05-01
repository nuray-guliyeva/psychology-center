package com.psychcenter.backend.controller;

import com.psychcenter.backend.dto.request.BookingRequestDto;
import com.psychcenter.backend.dto.response.BookingResponseDto;
import com.psychcenter.backend.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/slots")
    public List<LocalTime> getSlots(
            @RequestParam Long psychologistId,
            @RequestParam LocalDate date
    ) {
        return bookingService.getAvailableSlots(psychologistId, date);
    }

    @GetMapping("/calendar")
    public Map<LocalDate, List<BookingResponseDto>> calendar(
            @RequestParam Long psychologistId
    ) {
        return bookingService.getCalendar(psychologistId);
    }

    @PostMapping
    public BookingResponseDto create(
            @RequestBody @Valid BookingRequestDto dto,
            @AuthenticationPrincipal UserDetails user
    ) {
        return bookingService.create(dto, user.getUsername());
    }
}