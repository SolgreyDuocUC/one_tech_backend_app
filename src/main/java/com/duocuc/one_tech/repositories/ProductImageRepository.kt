package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.Product
import com.duocuc.one_tech.models.ProductImage
import org.springframework.data.jpa.repository.JpaRepository

interface ProductImageRepository : JpaRepository<ProductImage?, Long?> {
    fun findByProduct(product: Product?): MutableList<ProductImage?>?
}