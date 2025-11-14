package com.duocuc.one_tech.services;

import com.duocuc.one_tech.dto.productImage.ProductImageDTO;

import java.util.List;

public interface ProductImageService {

    List<ProductImageDTO> getImagesByProduct(Long productId);

    ProductImageDTO addImageToProduct(Long productId, String url, String alt, Boolean isMain);

    ProductImageDTO setMainImage(Long imageId);

    void deleteImage(Long imageId);
}
