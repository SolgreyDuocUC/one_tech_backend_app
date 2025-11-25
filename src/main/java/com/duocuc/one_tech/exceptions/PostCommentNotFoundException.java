package com.duocuc.one_tech.exceptions;


public class PostCommentNotFoundException extends RuntimeException {
    public PostCommentNotFoundException(Long id) {
        super("No se encontró el comentario con id: " + id);
    }
}
