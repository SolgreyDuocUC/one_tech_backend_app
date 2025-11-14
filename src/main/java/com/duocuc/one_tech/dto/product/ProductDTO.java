package com.duocuc.one_tech.dto.product;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        String name,
        String slug,
        String description,
        BigDecimal price

) {}
