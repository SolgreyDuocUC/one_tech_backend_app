package models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "regions")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @ToString
@Schema (description = "Entidad que representa un region")
public class Regions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_region")
    @Schema(description = "Codigo del region", example = "1")
    private Long idRegion;

    @Column(name = "code_region", nullable = false, length = 10)
    @NotBlank(message = "El código de la región no puede ser vacío")
    @Schema(description = "Código de la región", example = "RM")
    private String codeRegion;

    @Column(name = "name_region", nullable = false, length = 80)
    @NotBlank(message = "El nombre de la región no puede ser vacío")
    @Schema(description = "Nombre de la región", example = "Región Metropolitana")
    private String nameRegion;

    @OneToMany(
            mappedBy = "regions",
            cascade = CascadeType.ALL,
            orphanRemoval = false,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference("region-communes")
    @ToString.Exclude
    private List<Communes> communesLists = new ArrayList<>();

}
