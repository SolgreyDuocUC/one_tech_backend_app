package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.categories.CategoriesDTO;
import com.duocuc.one_tech.models.Category;
import com.duocuc.one_tech.services.CategoryService;
import jakarta.persistence.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/api/category")
public class CategoryController {

    private final CategoryService CategoryService;

    public CategoryController(CategoryService categoryService) {
        this.CategoryService = categoryService;
    }

    // v1/api/category/categories
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAll(){
        return ResponseEntity.ok(CategoryService.findAll());
    }



    //
}
