package models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Entity
@Table(name="communes")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @ToString
@Schema(description = "Entidad que representa un communes")

public class Communes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_communes")
    @Schema(description = "Codigo del Communes", example = "1")
    private Long idCommunes;

    @Column(name = "name_commune", nullable = false, length = 80)
    @NotBlank(message = "El nombre de la comuna no puede ser vacío")
    @Schema(description = "Nombre de la comuna", example = "Zapallar")
    private String nameCommune;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_region", nullable = false)
    @JsonBackReference("region-communes")
    @ToString.Exclude
    private Regions region;
}
