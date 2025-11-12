package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dtos.RoleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    public ResponseEntity<List<RoleDTO>> getAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body()
    }
}
