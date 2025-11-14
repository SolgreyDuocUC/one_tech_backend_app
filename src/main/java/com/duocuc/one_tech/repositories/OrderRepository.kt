package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.Order
import com.duocuc.one_tech.models.User

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order?, Long?> {
    fun findByUser(user: User?): MutableList<Order?>?
}