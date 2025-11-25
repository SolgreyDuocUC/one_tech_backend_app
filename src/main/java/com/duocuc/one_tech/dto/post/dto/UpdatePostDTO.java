package com.duocuc.one_tech.dto.post.dto;

import jakarta.validation.constraints.Size;

public record UpdatePostDTO(
        @Size(max = 200)
        String title,

        @Size(max = 600)
        String excerpt,

        @Size(max = 8000)
        String content,

        @Size(max = 512)
        String coverImageUrl,

        Boolean isPublished
) {}
