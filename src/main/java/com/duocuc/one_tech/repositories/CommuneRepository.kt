package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.Communes
import org.springframework.data.jpa.repository.JpaRepository

interface CommuneRepository : JpaRepository<Communes?, Long?>