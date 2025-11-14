package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.Post
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PostRepository : JpaRepository<Post?, Long?> {
    fun findBySlug(slug: String?): Optional<Post?>?
}