package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product?, Long?>