package com.duocuc.one_tech.dto.product;

import java.math.BigDecimal;

public record ProductCreateDTO(
        String name,
        String slug,
        String description,
        BigDecimal price,
        Integer stock,
        Integer stockCritico,
        Integer featuredProduct,
        Long categoryId,
        Long discountId

) {}
