package com.psychcenter.backend.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class SubmitTestRequestDto {

    private Long testId;

    private Map<Long, Long> answers;
}