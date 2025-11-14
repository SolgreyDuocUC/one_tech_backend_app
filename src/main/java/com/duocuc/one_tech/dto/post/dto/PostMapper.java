package com.duocuc.one_tech.dto.post.dto;

import com.duocuc.one_tech.dto.post.PostDTO;
import com.duocuc.one_tech.models.Post;

public class PostMapper {

    public static PostDTO toDto(Post post) {
        if (post == null) return null;

        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getSlug(),
                post.getExcerpt(),
                post.getContent(),
                post.getCoverImageUrl(),
                post.getPublishedAt(),
                post.getIsPublished(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
