package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User?, Long?>