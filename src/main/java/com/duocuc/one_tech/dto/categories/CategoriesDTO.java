package com.duocuc.one_tech.dto.categories;

import com.duocuc.one_tech.models.Category;
import com.duocuc.one_tech.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoriesDTO {

    Long id;
    String name;
    String slug;
    boolean isActive;
    List<Product> product = new ArrayList<>();
    List<Category> categories = new ArrayList<>();

}
