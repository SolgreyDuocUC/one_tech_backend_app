package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.userRole.UserRoleAssignmentDTO;
import com.duocuc.one_tech.services.UserRole.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user-roles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping
    public ResponseEntity<?> assignRole(@RequestBody UserRoleAssignmentDTO dto) {
        try {
            UserRoleAssignmentDTO saved = userRoleService.assignRoleToUser(dto);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<UserRoleAssignmentDTO>> getAllAssignments() {
        List<UserRoleAssignmentDTO> assignments = userRoleService.findAllAssignments();
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/{idUser}/{idRole}")
    public ResponseEntity<?> getAssignment(
            @PathVariable Long idUser,
            @PathVariable Long idRole
    ) {
        Optional<UserRoleAssignmentDTO> assignment =
                userRoleService.findAssignmentByIds(idUser, idRole);

        return assignment
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Asignación no encontrada"));
    }

    @PutMapping("/{idUser}/{idRole}")
    public ResponseEntity<?> updateGrantedAt(
            @PathVariable Long idUser,
            @PathVariable Long idRole,
            @RequestParam("date") String newDate
    ) {
        try {
            OffsetDateTime date = OffsetDateTime.parse(newDate);
            UserRoleAssignmentDTO updated =
                    userRoleService.updateGrantedAt(idUser, idRole, date);

            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{idUser}/{idRole}")
    public ResponseEntity<?> deleteAssignment(
            @PathVariable Long idUser,
            @PathVariable Long idRole
    ) {
        try {
            userRoleService.deleteAssignment(idUser, idRole);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

}
