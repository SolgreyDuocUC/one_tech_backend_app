package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.CartItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartItemRepository : JpaRepository<CartItem?, Long?>