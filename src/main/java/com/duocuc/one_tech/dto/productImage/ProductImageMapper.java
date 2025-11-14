package com.duocuc.one_tech.dto.productImage;

import com.duocuc.one_tech.dto.productImage.ProductImageDTO;
import com.duocuc.one_tech.models.ProductImage;
import com.duocuc.one_tech.models.Product;

public class ProductImageMapper {

    public static ProductImageDTO toDto(ProductImage entity) {
        if (entity == null) return null;

        Product product = entity.getProduct();
        Long productId = (product != null ? product.getId() : null);

        return new ProductImageDTO(
                entity.getId(),
                productId,
                entity.getUrl(),     // ← USANDO TU CAMPO REAL
                entity.getAlt(),     // ← USANDO TU CAMPO REAL
                entity.getIsMain()   // ← USANDO TU CAMPO REAL
        );
    }
}
