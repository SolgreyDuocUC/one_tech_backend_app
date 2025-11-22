package com.duocuc.one_tech.dto.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePostDTO(
        @NotBlank @Size(max = 200)
        String title,

        @NotBlank @Size(max = 200)
        String slug,

        @Size(max = 600)
        String excerpt,

        @Size(max = 8000)
        String content,

        @Size(max = 512)
        String coverImageUrl,

        Boolean isPublished
) {}