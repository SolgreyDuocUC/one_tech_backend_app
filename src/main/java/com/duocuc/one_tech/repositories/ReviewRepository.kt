package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.Product
import com.duocuc.one_tech.models.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review?, Long?> {
    fun findByProduct(product: Product?): MutableList<Review?>?
}