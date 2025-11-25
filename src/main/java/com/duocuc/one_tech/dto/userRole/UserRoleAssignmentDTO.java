package com.duocuc.one_tech.dto.userRole;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleAssignmentDTO {

    @NotNull(message = "El ID del usuario no puede ser nulo")
    private Long idUser;

    @NotNull(message = "El ID del rol no puede ser nulo")
    private Long idRole;

}
