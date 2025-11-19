package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.role.RoleDTO;
import com.duocuc.one_tech.models.Role;
import com.duocuc.one_tech.services.Role.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @PostMapping
    public ResponseEntity<?> createRole(@Valid @RequestBody RoleDTO roleDTO) {
        try {
            Role newRole = roleService.save(roleDTO);
            return new ResponseEntity<>(newRole, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) {
        Role role = roleService.findByName(name);
        return ResponseEntity.ok(role);
    }

}