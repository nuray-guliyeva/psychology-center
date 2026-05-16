package com.psychcenter.backend.dto.request;

import com.psychcenter.backend.model.enums.BlogCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BlogCreateRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private BlogCategory category;

    @NotNull
    private Long authorId;
}