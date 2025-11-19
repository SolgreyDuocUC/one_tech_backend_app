package com.duocuc.one_tech.services.Product;

import com.duocuc.one_tech.dto.product.ProductDTO;
import com.duocuc.one_tech.dto.product.ProductMapper;
import com.duocuc.one_tech.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.duocuc.one_tech.repositories.ProductRepository;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
    public ProductDTO crearProducto(ProductDTO dto) {
        Product p = ProductMapper.toEntity(dto);
        p.setId(null); // por si acaso
        Product guardado = productRepository.save(p);
        return ProductMapper.toDto(guardado);
    }

    @Override
    public ProductDTO actualizarProducto(Long id, ProductDTO dto) {
        Product existe = productRepository.findById(id)
                .orElseThrow(() -> new org.webjars.NotFoundException("Producto no encontrado con id " + id));

        // Actualiza SOLO los campos editables
        existe.setName(dto.name());
        existe.setSlug(dto.slug());
        existe.setDescription(dto.description());
        existe.setPrice(dto.price());

        Product actualizado = productRepository.save(existe);
        return ProductMapper.toDto(actualizado);
    }

    @Override
    public void eliminarProducto(Long id) {
        if (!productRepository.existsById(id)) {
            throw new org.webjars.NotFoundException("Producto no encontrado con id " + id);
        }
        productRepository.deleteById(id);
    }
}
