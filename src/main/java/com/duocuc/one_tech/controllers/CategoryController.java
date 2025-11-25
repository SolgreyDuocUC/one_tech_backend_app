package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.category.CategoryCreateDTO;
import com.duocuc.one_tech.dto.category.CategoryDTO;
import com.duocuc.one_tech.services.Category.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listarCategorias() {
        return ResponseEntity.ok(categoryService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> obtenerCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> crearCategoria(@RequestBody CategoryCreateDTO dto) {
        CategoryDTO creada = categoryService.crearCategoria(dto);
        URI location = URI.create("/api/categories/" + creada.id());
        return ResponseEntity.created(location).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> actualizarCategoria(@PathVariable Long id,
                                                           @RequestBody CategoryCreateDTO dto) {
        CategoryDTO actualizada = categoryService.actualizarCategoria(id, dto);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoryService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
