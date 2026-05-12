package com.psychcenter.backend.mapper;

import com.psychcenter.backend.dto.response.BookingResponseDto;
import com.psychcenter.backend.model.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingResponseDto toDto(Booking b) {
        return BookingResponseDto.builder()
                .id(b.getId())
                .psychologistName(b.getPsychologist().getFirstName())
                .date(b.getDate())
                .time(b.getTime())
                .status(b.getStatus())
                .build();
    }
}
