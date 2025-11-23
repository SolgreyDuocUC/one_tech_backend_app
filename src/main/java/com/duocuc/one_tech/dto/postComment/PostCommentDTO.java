package com.duocuc.one_tech.dto.postComment;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCommentDTO {
    private Long id;
    private String content;
    private OffsetDateTime createdAt;
    private Long postId;
    private Long authorId;
}
