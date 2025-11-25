package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface CategoryRepository : JpaRepository<Category?, Long?>
