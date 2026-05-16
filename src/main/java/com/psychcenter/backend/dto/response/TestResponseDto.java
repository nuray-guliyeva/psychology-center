package com.psychcenter.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestResponseDto {
    private Long id;
    private String name;
    private String description;
}