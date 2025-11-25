package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.post.*;
import com.duocuc.one_tech.dto.post.dto.PostCommentDTO;
import com.duocuc.one_tech.dto.post.dto.PostCommentRequest;
import com.duocuc.one_tech.dto.post.TagDTO;
import com.duocuc.one_tech.dto.post.dto.PostUpdateDTO;
import com.duocuc.one_tech.services.Blog.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
@CrossOrigin(origins = "*")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // POSTS

    // GET /api/blog/posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> listPosts() {
        return ResponseEntity.ok(blogService.listPosts());
    }

    // GET /api/blog/posts/{id}
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getPostById(id));
    }

    // GET /api/blog/posts/slug/{slug}
    @GetMapping("/posts/slug/{slug}")
    public ResponseEntity<PostDTO> getPostBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(blogService.getPostBySlug(slug));
    }

    // TAGS

    // GET /api/blog/tags
    @GetMapping("/tags")
    public ResponseEntity<List<TagDTO>> listTags() {
        return ResponseEntity.ok(blogService.listTags());
    }

    // COMMENTS

    // GET /api/blog/posts/{postId}/comments
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<PostCommentDTO>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(blogService.getCommentsByPost(postId));
    }

    // POST /api/blog/posts/{postId}/comments?userId=1
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<PostCommentDTO> addComment(@PathVariable Long postId,
                                                     @RequestParam Long userId,
                                                     @RequestBody PostCommentRequest request) {
        return ResponseEntity.ok(blogService.addComment(postId, userId, request));
    }

    // DELETE /api/blog/comments/{commentId}
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        blogService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
    // PUT /api/blog/posts/{id}
    @PutMapping("/posts/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id,
                                              @RequestBody PostUpdateDTO dto) {
        PostDTO updated = blogService.updatePost(id, dto);
        return ResponseEntity.ok(updated);
    }
}
