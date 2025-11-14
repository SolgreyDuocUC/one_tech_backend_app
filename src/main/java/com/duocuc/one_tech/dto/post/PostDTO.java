package com.duocuc.one_tech.dto.post;

import java.time.OffsetDateTime;

public record PostDTO(
        Long id,
        String title,
        String slug,
        String excerpt,
        String content,
        String coverImageUrl,
        OffsetDateTime publishedAt,
        Boolean isPublished,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {}
