package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.UserRole
import com.duocuc.one_tech.models.UserRoleId
import org.springframework.data.jpa.repository.JpaRepository

interface UserRoleRepository : JpaRepository<UserRole?, UserRoleId?>