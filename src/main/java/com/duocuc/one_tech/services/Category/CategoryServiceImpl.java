package com.duocuc.one_tech.services.Category;

import com.duocuc.one_tech.dto.category.CategoryDTO;
import com.duocuc.one_tech.dto.category.CreateCategoryDTO;
import com.duocuc.one_tech.dto.category.UpdateCategoryDTO;
import com.duocuc.one_tech.exceptions.ResourceNotFoundException;
import com.duocuc.one_tech.models.Category;
import com.duocuc.one_tech.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CreateCategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setSlug(dto.getSlug());
        category.setIsActive(dto.getIsActive());

        if (dto.getParentId() != null) {
            Category parent = categoryRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found with id " + dto.getParentId()));
            category.setParentCategory(parent);
        }

        return mapToDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
        assert category != null;
        return mapToDTO(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().filter(Objects::nonNull)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(Long id, UpdateCategoryDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));

        assert category != null;
        category.setName(dto.getName());
        category.setSlug(dto.getSlug());
        category.setIsActive(dto.getIsActive());

        if (dto.getParentId() != null) {
            Category parent = categoryRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found with id " + dto.getParentId()));
            category.setParentCategory(parent);
        } else {
            category.setParentCategory(null);
        }

        return mapToDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
        assert category != null;
        categoryRepository.delete(category);
    }

    // Mapper helper
    private CategoryDTO mapToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setSlug(category.getSlug());
        dto.setIsActive(category.getIsActive());
        dto.setParentId(category.getParentCategory() != null ? category.getParentCategory().getId() : null);
        dto.setChildCategories(
                category.getChildCategories().stream()
                        .map(this::mapToDTO)
                        .collect(Collectors.toList())
        );
        return dto;
    }
}
