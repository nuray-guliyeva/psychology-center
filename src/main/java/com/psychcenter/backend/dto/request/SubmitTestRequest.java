package com.psychcenter.backend.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class SubmitTestRequest {

    private Long testId;

    private Map<Long, Long> answers;
}