package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.productImage.ProductImageDTO;
import com.duocuc.one_tech.services.ProductImage.ProductImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")
public class ProductImageController {

    private final ProductImageService productImageService;

    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @GetMapping("/{productId}/images")
    public ResponseEntity<List<ProductImageDTO>> getImages(@PathVariable Long productId) {
        return ResponseEntity.ok(productImageService.getImagesByProduct(productId));
    }

    @PostMapping("/{productId}/images")
    public ResponseEntity<ProductImageDTO> addImage(
            @PathVariable Long productId,
            @RequestParam String url,
            @RequestParam(required = false) String alt,
            @RequestParam(required = false, defaultValue = "false") Boolean isMain
    ) {
        return ResponseEntity.ok(
                productImageService.addImageToProduct(productId, url, alt, isMain)
        );
    }

    @PutMapping("/images/{imageId}/set-main")
    public ResponseEntity<ProductImageDTO> setMain(@PathVariable Long imageId) {
        return ResponseEntity.ok(productImageService.setMainImage(imageId));
    }

    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<Void> delete(@PathVariable Long imageId) {
        productImageService.deleteImage(imageId);
        return ResponseEntity.noContent().build();
    }
}
