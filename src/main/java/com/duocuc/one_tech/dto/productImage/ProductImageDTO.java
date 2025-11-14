package com.duocuc.one_tech.dto.productImage;

public record ProductImageDTO(
        Long id,
        Long productId,
        String url,
        String alt,
        Boolean isMain
) {}
