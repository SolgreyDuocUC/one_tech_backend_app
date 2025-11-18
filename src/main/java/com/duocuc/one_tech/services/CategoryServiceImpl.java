package com.duocuc.one_tech.services;

import com.duocuc.one_tech.models.Category;
import com.duocuc.one_tech.models.Product;
import com.duocuc.one_tech.repositories.CategoryRepository;
import com.duocuc.one_tech.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public String findByCategoryName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    @Override
    public Category saveByCategoryId(Long id) {
        throw new UnsupportedOperationException("No se puede guardar solo por ID");
    }

    @Override
    public Category saveByCategoryName(String name) {
        Category category = new Category();
        category.setCategoryName(name);
        return categoryRepository.save(category);
    }

    @Override
    public Category deleteByCategoryId(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            categoryRepository.delete(category);
        }
        return category;
    }

    @Override
    public Category deleteByCategoryName(String name) {
        Category category = null;
        categoryRepository.findByCategoryName(name);
        return null;
    }

    @Override
    public Category updateByCategoryId(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            categoryRepository.save(category);
        }
        return category;
    }

    @Override
    public Category updateByCategoryName(String name) {
        Category category = null;
        categoryRepository.findByCategoryName(name);
        return null;
    }
}
