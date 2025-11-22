package com.duocuc.one_tech.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record PostDTO(
        Long id,

        @NotBlank(message = "El título no puede estar vacío.")
        @Size(min = 1, max = 200)
        String title,

        @NotBlank(message = "El slug no puede estar vacío.")
        @Size(min = 1, max = 200)
        String slug,

        @Size(max = 600)
        String excerpt,

        @Size(max = 8000)
        String content,

        @Size(max = 512)
        String coverImageUrl,

        @NotNull(message = "El estado de publicación es requerido.")
        Boolean isPublished,

        OffsetDateTime publishedAt,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {}
