package com.duocuc.one_tech.dtos;

import com.duocuc.one_tech.models.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleDTO {

    @NotNull(message = "El campo de nombre no puede estar vacío")
    private String name;

    private String description;

    @NotNull(message = "Debe asignar un rol para continuar")
    private Set<UserRole> userRoles = new HashSet<>();

}
