package com.psychcenter.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VacancyResponseDto {

    private Long id;
    private String position;
    private String location;
    private String type;
    private String description;
}