package com.duocuc.one_tech.dto.role;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RoleDTO {

    @NotNull(message = "El nombre del rol no puede estar vacío")
    private String name;

    @NotNull(message = "La descripción del rol no puede estar vacía")
    private String description;

    // CONSTRUCTOR PARA MAPEO
    public RoleDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

