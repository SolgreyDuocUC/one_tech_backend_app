package com.duocuc.one_tech.services.Product;

import com.duocuc.one_tech.dto.product.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> listarProductos();

    ProductDTO obtenerPorId(Long id);

    ProductDTO crearProducto(ProductDTO dto);

    ProductDTO actualizarProducto(Long id, ProductDTO dto);

    void eliminarProducto(Long id);
}
