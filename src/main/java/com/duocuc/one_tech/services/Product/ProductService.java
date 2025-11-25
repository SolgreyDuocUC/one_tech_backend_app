package com.duocuc.one_tech.services.Product;

import com.duocuc.one_tech.dto.product.ProductCreateDTO;
import com.duocuc.one_tech.dto.product.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> listarProductos();

    ProductDTO obtenerPorId(Long id);

    ProductDTO crearProducto(ProductCreateDTO dto);

    ProductDTO actualizarProducto(Long id, ProductCreateDTO dto);

    void eliminarProducto(Long id);
}

