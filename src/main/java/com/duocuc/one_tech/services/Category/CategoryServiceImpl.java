package com.duocuc.one_tech.services.Category;

import com.duocuc.one_tech.dto.category.CategoryCreateDTO;
import com.duocuc.one_tech.dto.category.CategoryDTO;
import com.duocuc.one_tech.models.Category;
import com.duocuc.one_tech.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private CategoryDTO toDTO(Category c) {
        Long parentId = c.getParentCategory() != null ? c.getParentCategory().getId() : null;
        return new CategoryDTO(
                c.getId(),
                c.getName(),
                c.getSlug(),
                c.getIsActive(),
                parentId
        );
    }

    @Override
    public List<CategoryDTO> listarCategorias() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public CategoryDTO obtenerPorId(Long id) {
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        return toDTO(c);
    }

    @Override
    public CategoryDTO crearCategoria(CategoryCreateDTO dto) {

        Category parent = null;
        if (dto.parentId() != null) {
            parent = categoryRepository.findById(dto.parentId())
                    .orElseThrow(() -> new RuntimeException("Categoría padre no encontrada"));
        }

        Category c = new Category();
        c.setName(dto.name());
        c.setSlug(dto.slug());
        c.setIsActive(dto.isActive());
        c.setParentCategory(parent);

        Category saved = categoryRepository.save(c);
        return toDTO(saved);
    }

    @Override
    public CategoryDTO actualizarCategoria(Long id, CategoryCreateDTO dto) {

        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        existing.setName(dto.name());
        existing.setSlug(dto.slug());
        existing.setIsActive(dto.isActive());

        if (dto.parentId() != null) {
            Category parent = categoryRepository.findById(dto.parentId())
                    .orElseThrow(() -> new RuntimeException("Categoría padre no encontrada"));
            existing.setParentCategory(parent);
        } else {
            existing.setParentCategory(null);
        }

        Category updated = categoryRepository.save(existing);
        return toDTO(updated);
    }

    @Override
    public void eliminarCategoria(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada");
        }
        categoryRepository.deleteById(id);
    }
}
