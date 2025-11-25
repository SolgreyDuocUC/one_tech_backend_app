package com.duocuc.one_tech.dto.post.dto;

public record PostUpdateDTO(
        String title,
        String slug,
        String excerpt,
        String content,
        String coverImageUrl,
        Boolean isPublished
) {}
