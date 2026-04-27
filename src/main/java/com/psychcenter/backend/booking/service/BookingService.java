package com.psychcenter.backend.booking.service;

import com.psychcenter.backend.booking.dto.BookingRequestDto;
import com.psychcenter.backend.booking.dto.BookingResponseDto;

public interface BookingService {

    BookingResponseDto create(BookingRequestDto dto, String userEmail);
}