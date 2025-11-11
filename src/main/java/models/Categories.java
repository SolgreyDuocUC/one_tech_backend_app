package models;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
@Entity
@Table(name="categories")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @ToString
@Schema(description = "Entidad que representa un categoria")
public class Categories {
    @Id
    private Long idCategory;
    private String name;
    private String slug;

}
