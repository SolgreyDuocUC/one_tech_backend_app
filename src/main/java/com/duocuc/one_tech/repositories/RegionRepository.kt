package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.Region
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RegionRepository : JpaRepository<Region?, Long?> {

    fun findByCode(code: String?): Optional<Region?>?

    fun findByName(name: String?): Optional<Region?>?
}