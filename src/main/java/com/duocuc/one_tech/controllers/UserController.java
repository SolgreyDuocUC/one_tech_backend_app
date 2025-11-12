package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.models.User;
import com.duocuc.one_tech.services.UserService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findById(id));
    }

    @GetMapping("/{rut}")
    public ResponseEntity<User> findByRun(@PathVariable String run){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findByRun(run));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<User>> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody User user){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.save(user));
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateById(@PathVariable Long id, @Valid @RequestBody User user){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.updateById(id, user));
    }



}
