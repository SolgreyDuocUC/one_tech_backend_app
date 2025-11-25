package com.duocuc.one_tech.services.PostComment;

import com.duocuc.one_tech.dto.post.dto.PostCommentDTO;
import com.duocuc.one_tech.dto.postComment.CreatePostCommentDTO;

import java.util.List;

public interface PostCommentService {
    PostCommentDTO createComment(CreatePostCommentDTO dto);
    PostCommentDTO getCommentById(Long id);
    List<PostCommentDTO> getAllComments();
    PostCommentDTO updateComment(Long id, CreatePostCommentDTO dto);
    void deleteComment(Long id);
}
