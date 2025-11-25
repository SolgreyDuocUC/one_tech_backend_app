package com.duocuc.one_tech.exceptions;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long id) {
        super("No se encontró el post con id: " + id);
    }
}

