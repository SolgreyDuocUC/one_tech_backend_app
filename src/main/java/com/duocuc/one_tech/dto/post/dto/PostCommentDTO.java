package com.duocuc.one_tech.dto.post.dto;

import java.time.OffsetDateTime;

public record PostCommentDTO(
        Long id,
        Long postId,
        Long userId,
        String userName,
        String comment,
        OffsetDateTime createdAt
) {}
