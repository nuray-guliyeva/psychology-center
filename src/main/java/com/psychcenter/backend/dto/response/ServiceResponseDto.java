package com.psychcenter.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceResponseDto {

    private Long id;
    private String title;
    private String description;
    private String audience;
    private String format;
    private Double price;
}