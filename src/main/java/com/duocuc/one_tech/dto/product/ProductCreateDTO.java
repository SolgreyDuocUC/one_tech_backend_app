package com.duocuc.one_tech.dto.product;

import java.math.BigDecimal;

public record ProductCreateDTO(
        Long id,
        String name,
        String slug,
        String description,
        BigDecimal price

) {}
