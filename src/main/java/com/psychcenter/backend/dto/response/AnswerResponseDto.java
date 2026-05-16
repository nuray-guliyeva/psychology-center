package com.psychcenter.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerResponseDto {
    private Long id;
    private String text;
    private int score;
}