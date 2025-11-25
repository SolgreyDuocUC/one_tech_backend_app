package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.Post
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PostRepository : JpaRepository<Post?, Long?> {
    fun findBySlug(slug: String?): Optional<Post?>?
    fun existsBySlug(slug: @jakarta.validation.constraints.NotBlank @jakarta.validation.constraints.Size(max = 200) String): Boolean
}