package com.duocuc.one_tech.dto.post.dto;

import com.duocuc.one_tech.models.Post;
import com.duocuc.one_tech.models.PostComment;
import com.duocuc.one_tech.models.User;

public class PostCommentMapper {

    public static PostCommentDTO toDto(PostComment comment) {
        if (comment == null) return null;

        Post post = comment.getPost();
        User user = comment.getAuthor();

        Long postId = post != null ? post.getId() : null;
        Long userId = user != null ? user.getId() : null;

        // Ajusta el nombre según tu User (puede ser getFullNameUsers(), getNameUsers(), etc.)
        String userName = user != null ? user.getFirstName() : null;

        return new PostCommentDTO(
                comment.getId(),
                postId,
                userId,
                userName,
                comment.getContent(),     // ajusta getter
                comment.getCreatedAt()    // o null si no tienes campo
        );
    }
}
