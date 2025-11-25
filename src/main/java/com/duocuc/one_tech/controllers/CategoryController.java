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
import com.duocuc.one_tech.dto.category.CategoryDTO;
import com.duocuc.one_tech.dto.category.CreateCategoryDTO;
import com.duocuc.one_tech.dto.category.UpdateCategoryDTO;
import com.duocuc.one_tech.services.Category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
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
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CreateCategoryDTO dto) {
        return ResponseEntity.ok(categoryService.createCategory(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryDTO dto) {
        return ResponseEntity.ok(categoryService.updateCategory(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}

