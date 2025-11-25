package com.duocuc.one_tech.services.Product;

import com.duocuc.one_tech.dto.product.ProductCreateDTO;
import com.duocuc.one_tech.dto.product.ProductDTO;
import com.duocuc.one_tech.dto.product.ProductMapper;
import com.duocuc.one_tech.models.Category;
import com.duocuc.one_tech.models.Discount;
import com.duocuc.one_tech.models.Product;
import com.duocuc.one_tech.repositories.CategoryRepository;
import com.duocuc.one_tech.repositories.DiscountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.duocuc.one_tech.repositories.ProductRepository;


import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final DiscountRepository discountRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, DiscountRepository discountRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.discountRepository = discountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> listarProductos() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO obtenerPorId(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new org.webjars.NotFoundException("Producto no encontrado con id " + id));
        return ProductMapper.toDto(p);
    }

    @Override
    public ProductDTO crearProducto(ProductCreateDTO dto) {

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Product product = new Product();
        product.setName(dto.name());
        product.setSlug(dto.slug());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        product.setStockCritico(dto.stockCritico());
        product.setFeaturedProduct(dto.featuredProduct());
        product.setCategory(category);

        if (dto.discountId() != null) {
            Discount discount = discountRepository.findById(dto.discountId())
                    .orElseThrow(() -> new RuntimeException("Descuento no encontrado"));
            product.setDiscount(discount);
        }

        Product saved = productRepository.save(product);

        return new ProductDTO(
                saved.getId(),
                saved.getName(),
                saved.getSlug(),
                saved.getDescription(),
                saved.getPrice()
        );
    }

    @Override
    public ProductDTO actualizarProducto(Long id, ProductCreateDTO dto) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        existing.setName(dto.name());
        existing.setSlug(dto.slug());
        existing.setDescription(dto.description());
        existing.setPrice(dto.price());
        existing.setStock(dto.stock());
        existing.setStockCritico(dto.stockCritico());
        existing.setFeaturedProduct(dto.featuredProduct());
        existing.setCategory(category);

        if (dto.discountId() != null) {
            Discount discount = discountRepository.findById(dto.discountId())
                    .orElseThrow(() -> new RuntimeException("Descuento no encontrado"));
            existing.setDiscount(discount);
        } else {
            existing.setDiscount(null);
        }

        Product updated = productRepository.save(existing);

        return new ProductDTO(
                updated.getId(),
                updated.getName(),
                updated.getSlug(),
                updated.getDescription(),
                updated.getPrice()
        );
    }


    @Override
    public void eliminarProducto(Long id) {
        if (!productRepository.existsById(id)) {
            throw new org.webjars.NotFoundException("Producto no encontrado con id " + id);
        }
        productRepository.deleteById(id);
    }
}
