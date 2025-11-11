package models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.type.descriptor.jdbc.TimestampWithTimeZoneJdbcType;

@Entity
@Table(name = "addresses")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @ToString
@Schema(description = "Entidad que representa direccion")
public class Addresses {
    @Id
    private Long id;
    private Long idAddress;
    private String idUser;
    private String idCommune;
    private String line1;
    private String line2;
    private String postalCode;
    private Boolean isDefault;
    private TimestampWithTimeZoneJdbcType createdAt;
}
