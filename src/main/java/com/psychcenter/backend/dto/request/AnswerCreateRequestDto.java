package com.psychcenter.backend.dto.request;

import lombok.Data;

@Data
public class AnswerCreateRequestDto {
    private String text;
    private int score;
}