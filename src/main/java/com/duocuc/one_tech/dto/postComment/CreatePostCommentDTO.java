package com.duocuc.one_tech.dto.postComment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePostCommentDTO {
    @NotBlank
    private String content;

    @NotNull
    private Long postId;

    @NotNull
    private Long authorId;

    private Boolean isVisible = true;

}
