package com.duocuc.one_tech.services.Category;

import com.duocuc.one_tech.dto.category.CategoryDTO;
import com.duocuc.one_tech.dto.category.CreateCategoryDTO;
import com.duocuc.one_tech.dto.category.UpdateCategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CreateCategoryDTO createCategoryDTO);
    CategoryDTO getCategoryById(Long id);
    List<CategoryDTO> getAllCategories();
    CategoryDTO updateCategory(Long id, UpdateCategoryDTO updateCategoryDTO);
    void deleteCategory(Long id);
}