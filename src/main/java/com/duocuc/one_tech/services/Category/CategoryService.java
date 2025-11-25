package com.duocuc.one_tech.services.Category;

import com.duocuc.one_tech.dto.category.CategoryCreateDTO;
import com.duocuc.one_tech.dto.category.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> listarCategorias();

    CategoryDTO obtenerPorId(Long id);

    CategoryDTO crearCategoria(CategoryCreateDTO dto);

    CategoryDTO actualizarCategoria(Long id, CategoryCreateDTO dto);

    void eliminarCategoria(Long id);
}
