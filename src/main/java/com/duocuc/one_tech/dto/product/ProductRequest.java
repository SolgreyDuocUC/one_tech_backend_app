package com.duocuc.one_tech.dto;

import java.math.BigDecimal;

public class ProductRequest {

    private String name;
    private String slug;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Integer stockCritico;
    private Integer featuredProduct;
    private Long categoryId;   // clave

    private Long discountId;   // opcional

    // getters y setters
}
