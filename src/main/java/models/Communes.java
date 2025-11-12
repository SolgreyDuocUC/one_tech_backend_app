package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="COMMUNES")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @ToString
@Schema(description = "Entidad que representa un communes")

public class Communes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commune")
    @Schema(description = "Codigo del Communes", example = "1")
    private Long id;

    @Column(name = "name_commune", nullable = false, length = 80)
    @NotBlank(message = "El nombre de la comuna no puede ser vacío")
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_region", nullable = false)
    @JsonBackReference("region-communes")
    private Region region;

    @OneToMany(mappedBy = "commune")
    @JsonManagedReference("commune-addresses")
    private List<Addresses> addresses = new ArrayList<>();
}
