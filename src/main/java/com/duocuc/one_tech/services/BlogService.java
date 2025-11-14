package com.duocuc.one_tech.services;

import com.duocuc.one_tech.dto.post.*;
import com.duocuc.one_tech.dto.post.dto.PostCommentDTO;
import com.duocuc.one_tech.dto.post.dto.PostCommentRequest;
import com.duocuc.one_tech.dto.post.TagDTO;

import java.util.List;

public interface BlogService {

    // POSTS
    List<PostDTO> listPosts();
    PostDTO getPostById(Long id);
    PostDTO getPostBySlug(String slug);

    // TAGS
    List<TagDTO> listTags();

    // COMMENTS
    List<PostCommentDTO> getCommentsByPost(Long postId);
    PostCommentDTO addComment(Long postId, Long userId, PostCommentRequest request);
    void deleteComment(Long commentId);
}
