package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.Cart
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository : JpaRepository<Cart?, Long?>