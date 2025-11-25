package com.duocuc.one_tech.dto.category;

public record CategoryCreateDTO(
        String name,
        String slug,
        Boolean isActive,
        Long parentId   // puede ser null si no tiene padre
) {}
