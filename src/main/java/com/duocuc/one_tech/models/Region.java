package com.duocuc.one_tech.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "REGIONS")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @ToString
@Schema (description = "Entidad que representa un region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_region")
    @Schema(description = "Codigo del region", example = "1")
    private Long idRegion;

    @Column(name = "code_region", nullable = false, length = 10)
    @NotBlank(message = "El código de la región no puede ser vacío")
    private String code;

    @Column(name = "name_region", nullable = false, length = 80)
    @NotBlank(message = "El nombre de la región no puede ser vacío")
    private String name;

    @OneToMany(mappedBy = "region")
    @JsonManagedReference("region-communes")
    private List<Communes> communesLists = new ArrayList<>();

}
