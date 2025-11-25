package com.duocuc.one_tech.dto.category;

public record CategoryDTO(
        Long id,
        String name,
        String slug,
        Boolean isActive,
        Long parentId
) {}
