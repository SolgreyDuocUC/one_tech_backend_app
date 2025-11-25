package com.duocuc.one_tech.controllers;


import com.duocuc.one_tech.dto.post.dto.PostCommentDTO;
import com.duocuc.one_tech.dto.postComment.CreatePostCommentDTO;
import com.duocuc.one_tech.services.PostComment.PostCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class PostCommentController {

    private final PostCommentService commentService;

    @PostMapping
    public ResponseEntity<PostCommentDTO> createComment(@Validated @RequestBody CreatePostCommentDTO dto) {
        return new ResponseEntity<>(commentService.createComment(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostCommentDTO> getCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @GetMapping
    public ResponseEntity<List<PostCommentDTO>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostCommentDTO> updateComment(@PathVariable Long id,
                                                        @Validated @RequestBody CreatePostCommentDTO dto) {
        return ResponseEntity.ok(commentService.updateComment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}

