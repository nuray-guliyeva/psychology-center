package com.psychcenter.backend.service;

import com.psychcenter.backend.dto.request.BookingRequestDto;
import com.psychcenter.backend.dto.response.BookingResponseDto;

public interface BookingService {
    BookingResponseDto create(BookingRequestDto dto, String email);
}
