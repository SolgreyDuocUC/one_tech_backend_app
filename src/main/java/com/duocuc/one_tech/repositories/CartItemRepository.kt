package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.CartItem
import org.springframework.data.jpa.repository.JpaRepository

interface CartItemRepository : JpaRepository<CartItem?, Long?>