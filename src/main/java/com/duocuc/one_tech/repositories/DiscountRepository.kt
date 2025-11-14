package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.Discount
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DiscountRepository : JpaRepository<Discount?, Long?> {
    fun findByCode(code: String?): Optional<Discount?>?
}