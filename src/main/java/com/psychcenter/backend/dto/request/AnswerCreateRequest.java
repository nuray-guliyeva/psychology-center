package com.psychcenter.backend.dto.request;

import lombok.Data;

@Data
public class AnswerCreateRequest {
    private String text;
    private int score;
}