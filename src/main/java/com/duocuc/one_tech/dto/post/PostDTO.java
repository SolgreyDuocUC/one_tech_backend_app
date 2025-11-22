package com.duocuc.one_tech.dto.post;

import java.time.OffsetDateTime;

public record PostDTO(
        Long id,
        String title,
        String slug,
        String excerpt,
        String content,
        String coverImageUrl,
        Boolean isPublished,
        OffsetDateTime publishedAt,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {}
