package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.product.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.duocuc.one_tech.services.Product.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<ProductDTO>> listarProductos() {
        List<ProductDTO> productos = productService.listarProductos();
        return ResponseEntity.ok(productos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> obtenerProducto(@PathVariable Long id) {
        ProductDTO dto = productService.obtenerPorId(id);
        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<ProductDTO> crearProducto(@RequestBody ProductDTO dto) {
        ProductDTO creado = productService.crearProducto(dto);

        URI location = URI.create("/api/products/" + creado.id());
        return ResponseEntity.created(location).body(creado);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> actualizarProducto(@PathVariable Long id,
                                                         @RequestBody ProductDTO dto) {
        ProductDTO actualizado = productService.actualizarProducto(id, dto);
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
