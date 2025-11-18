package com.duocuc.one_tech.services;

import com.duocuc.one_tech.models.Category;
import com.duocuc.one_tech.models.Product;

import java.util.List;

public interface CategoryService {

    //Listar productos

    List<Category> findAll();

    List<Product> findAllProducts();

    //Buscar por ID

    Category findCategoryById(Long id);

    String findByCategoryName(String name);

    //Guardar por ID

    Category saveByCategoryId(Long id);

    Category saveByCategoryName(String name);

    //Eliminar

    Category deleteByCategoryId(Long id);
    Category deleteByCategoryName(String name);

    //Actualizar

    Category updateByCategoryId(Long id);
    Category updateByCategoryName(String name);

}
