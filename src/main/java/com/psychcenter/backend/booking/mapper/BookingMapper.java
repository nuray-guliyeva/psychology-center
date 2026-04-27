package com.psychcenter.backend.booking.mapper;

import com.psychcenter.backend.booking.dto.BookingResponseDto;
import com.psychcenter.backend.booking.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingResponseDto toDto(Booking booking) {
        return BookingResponseDto.builder()
                .id(booking.getId())
                .psychologistName(
                        booking.getPsychologist().getFirstName() + " " +
                                booking.getPsychologist().getLastName()
                )
                .date(booking.getDate())
                .time(booking.getTime())
                .status(booking.getStatus())
                .build();
    }
}