package com.duocuc.one_tech.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORIES")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @ToString
@Schema(description = "Entidad que representa una categoría con soporte jerárquico")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long id;

    @Column(name = "name_category", length = 80, nullable = false)
    private String name;

    @Column(name = "slug_category", length = 80, nullable = false)
    private String slug;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parent")
    @JsonBackReference("category-parent")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("category-parent")
    private List<Category> childCategories = new ArrayList<>();

//    @OneToMany(mappedBy = "category")
//    @JsonManagedReference("category-products")
//    private List<Product> products = new ArrayList<>();
}