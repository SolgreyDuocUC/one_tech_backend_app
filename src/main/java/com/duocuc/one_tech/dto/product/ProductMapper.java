package com.duocuc.one_tech.dto.product;

import com.duocuc.one_tech.models.Product;

public class ProductMapper {

    public static ProductDTO toDto(Product product) {
        if (product == null) return null;

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getSlug(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public static Product toEntity(ProductDTO dto) {
        if (dto == null) return null;

        Product p = new Product();
        p.setId(dto.id());
        p.setName(dto.name());
        p.setSlug(dto.slug());
        p.setDescription(dto.description());
        p.setPrice(dto.price());
        return p;
    }
}
