package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.post.PostDTO;
import com.duocuc.one_tech.dto.post.dto.CreatePostDTO;
import com.duocuc.one_tech.dto.post.dto.UpdatePostDTO;
import com.duocuc.one_tech.services.Post.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // Crear un nuevo post
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody CreatePostDTO dto) {
        PostDTO createdPost = postService.create(dto);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    // Obtener todos los posts
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.findAll();
        return ResponseEntity.ok(posts);
    }

    // Obtener un post por ID
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        PostDTO post = postService.findById(id);
        return ResponseEntity.ok(post);
    }

    // Obtener un post por slug
    @GetMapping("/slug/{slug}")
    public ResponseEntity<PostDTO> getPostBySlug(@PathVariable String slug) {
        PostDTO post = postService.findBySlug(slug);
        return ResponseEntity.ok(post);
    }

    // Actualizar un post
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody UpdatePostDTO dto) {
        PostDTO updatedPost = postService.update(id, dto);
        return ResponseEntity.ok(updatedPost);
    }

    // Eliminar un post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

