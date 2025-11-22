package com.duocuc.one_tech.dto.communes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommunesDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotNull(message = "La región es obligatoria")
    private Long regionId;
}
