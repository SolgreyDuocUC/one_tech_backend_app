package com.duocuc.one_tech.repositories

import com.duocuc.one_tech.models.Post
import com.duocuc.one_tech.models.PostComment
import org.springframework.data.jpa.repository.JpaRepository

interface PostCommentRepository : JpaRepository<PostComment?, Long?> {
    fun findByPost(post: Post?): MutableList<PostComment?>?
}