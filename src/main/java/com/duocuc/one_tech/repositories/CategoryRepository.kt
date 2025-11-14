package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category?, Long?>