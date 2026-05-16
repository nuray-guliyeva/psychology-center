package com.psychcenter.backend.dto.response;

import com.psychcenter.backend.model.enums.TestLevel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TestResultResponseDto {
    private String testName;
    private int score;
    private TestLevel level;
    private LocalDateTime date;
}