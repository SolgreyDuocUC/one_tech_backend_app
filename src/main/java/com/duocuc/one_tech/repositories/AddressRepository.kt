package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.Addresses
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepository : JpaRepository<Addresses?, Long?>