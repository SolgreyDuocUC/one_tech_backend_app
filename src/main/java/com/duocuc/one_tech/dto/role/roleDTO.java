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
public class roleDTO {

    private Long idRole;

    @NotNull(message = "El nombre del rol no puede estar vacío")
    private String name;

    @NotNull(message = "La descripción del rol no puede estar vacía")
    private String description;

    // Constructor para crear o recibir datos
    public roleDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Constructor completo: útil para mapear entidades a DTO
    public roleDTO(Long idRole, String name, String description) {
        this.idRole = idRole;
        this.name = name;
        this.description = description;
    }
}
