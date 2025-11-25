package com.duocuc.one_tech.dto.region;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegionDTO {

    @NotBlank(message = "El código no puede ser vacío")
    private String code;

    @NotBlank(message = "El nombre no puede ser vacío")
    private String name;
}

