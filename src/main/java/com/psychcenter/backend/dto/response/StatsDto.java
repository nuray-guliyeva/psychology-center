package com.psychcenter.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatsDto {

    private long users;
    private long bookings;
    private long psychologists;
}