package com.psychcenter.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionResponseDto {
    private Long id;
    private String text;
}