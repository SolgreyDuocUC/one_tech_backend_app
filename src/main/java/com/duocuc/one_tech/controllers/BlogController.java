package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.post.*;
import com.duocuc.one_tech.dto.post.dto.PostCommentDTO;
import com.duocuc.one_tech.dto.post.dto.PostCommentRequest;
import com.duocuc.one_tech.dto.post.TagDTO;
import com.duocuc.one_tech.services.Blog.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
@CrossOrigin(origins = "*")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> listarPosts() {
        List<PostDTO> posts = blogService.listPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDTO> obtenerPost(@PathVariable Long id) {
        PostDTO post = blogService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts/slug/{slug}")
    public ResponseEntity<PostDTO> obtenerPostPorSlug(@PathVariable String slug) {
        PostDTO post = blogService.getPostBySlug(slug);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/tags")
    public ResponseEntity<List<TagDTO>> listarTags() {
        List<TagDTO> tags = blogService.listTags();
        return ResponseEntity.ok(tags);
    }


    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<PostCommentDTO>> listarComentarios(@PathVariable Long postId) {
        List<PostCommentDTO> comentarios = blogService.getCommentsByPost(postId);
        return ResponseEntity.ok(comentarios);
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<PostCommentDTO> crearComentario(@PathVariable Long postId,
                                                          @RequestParam Long userId,
                                                          @RequestBody PostCommentRequest request) {
        PostCommentDTO creado = blogService.addComment(postId, userId, request);
        URI location = URI.create("/api/v1/blog/comments/" + creado.id());
        return ResponseEntity.created(location).body(creado);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long commentId) {
        blogService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
