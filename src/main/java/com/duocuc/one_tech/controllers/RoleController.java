package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.role.RoleDTO;
import com.duocuc.one_tech.models.Role;
import com.duocuc.one_tech.services.Role.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.duocuc.one_tech.services.Role.RoleServiceImpl;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@CrossOrigin(origins = "*")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> listarRoles() {
        return ResponseEntity.ok(roleService.listarRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.obtenerPorId(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RoleDTO> obtenerPorNombre(@PathVariable String name) {
        return ResponseEntity.ok(roleService.obtenerPorNombre(name));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> crearRole(@RequestBody RoleDTO dto) {

        RoleDTO creado = roleService.crearRole(dto);

        URI location = URI.create("/api/v1/roles/name/" + creado.getName());
        return ResponseEntity.created(location).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> actualizarRole(
            @PathVariable Long id,
            @RequestBody RoleDTO dto) {

        RoleDTO actualizado = roleService.actualizarRole(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRole(@PathVariable Long id) {
        roleService.eliminarRole(id);
        return ResponseEntity.noContent().build();
    }
}
