package com.psychcenter.backend.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestSubmitRequestDto {

    @NotBlank(message = "Test name is required")
    private String testName;

    @Min(value = 0, message = "Score cannot be negative")
    @Max(value = 100, message = "Score cannot be more than 100")
    private int score;

}