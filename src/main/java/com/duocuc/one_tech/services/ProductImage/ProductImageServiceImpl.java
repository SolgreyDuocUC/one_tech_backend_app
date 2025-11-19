package com.duocuc.one_tech.services.ProductImage;

import com.duocuc.one_tech.dto.productImage.ProductImageDTO;
import com.duocuc.one_tech.dto.productImage.ProductImageMapper;
import com.duocuc.one_tech.exceptions.NotFoundException;
import com.duocuc.one_tech.models.Product;
import com.duocuc.one_tech.models.ProductImage;
import com.duocuc.one_tech.repositories.ProductImageRepository;
import com.duocuc.one_tech.repositories.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductImageServiceImpl(ProductRepository productRepository,
                                   ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductImageDTO> getImagesByProduct(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + productId));

        return productImageRepository.findByProduct(product)
                .stream()
                .map(ProductImageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductImageDTO addImageToProduct(Long productId, String url, String alt, Boolean isMain) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id " + productId));

        ProductImage img = new ProductImage();
        img.setProduct(product);
        img.setUrl(url);     // ← tus nombres reales
        img.setAlt(alt);
        img.setIsMain(Boolean.TRUE.equals(isMain));

        // Si se marcó como principal → desmarcamos las otras
        if (Boolean.TRUE.equals(isMain)) {
            List<ProductImage> existing = productImageRepository.findByProduct(product);
            for (ProductImage other : existing) {
                other.setIsMain(false);
            }
            productImageRepository.saveAll(existing);
        }

        ProductImage saved = productImageRepository.save(img);
        return ProductImageMapper.toDto(saved);
    }

    @Override
    public ProductImageDTO setMainImage(Long imageId) {

        ProductImage img = productImageRepository.findById(imageId)
                .orElseThrow(() -> new NotFoundException("Imagen no encontrada con id " + imageId));

        Product product = img.getProduct();
        if (product == null) {
            throw new IllegalStateException("La imagen no pertenece a un producto.");
        }

        List<ProductImage> existing = productImageRepository.findByProduct(product);
        for (ProductImage other : existing) {
            other.setIsMain(other.getId().equals(imageId));
        }

        productImageRepository.saveAll(existing);

        return ProductImageMapper.toDto(img);
    }

    @Override
    public void deleteImage(Long imageId) {
        if (!productImageRepository.existsById(imageId)) {
            throw new NotFoundException("Imagen no encontrada con id " + imageId);
        }
        productImageRepository.deleteById(imageId);
    }
}
