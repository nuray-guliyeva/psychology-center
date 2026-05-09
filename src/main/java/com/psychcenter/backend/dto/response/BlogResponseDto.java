package com.psychcenter.backend.dto.response;

import com.psychcenter.backend.model.enums.BlogCategory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BlogResponseDto {

    private Long id;
    private String title;
    private String content;
    private BlogCategory category;
    private String author;
    private LocalDateTime createdAt;
}