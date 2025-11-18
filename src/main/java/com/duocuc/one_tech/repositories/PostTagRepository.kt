package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.Post
import org.springframework.data.jpa.repository.JpaRepository

public interface PostTagRepository : JpaRepository<Post, Long>