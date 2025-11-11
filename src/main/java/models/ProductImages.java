package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product_images")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @ToString
@Schema(description = "Entidad que representa un product_image")
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_productImages")
    @Schema(description = "Codigo del productImages", example = "1")
    private Long idProductImages;

    @Column(name = "id_product_image")
    @NotBlank(message = "El campo product Image no puede ser vacio")
    @Schema(description = "ProductImages del producto", example = "sillagamer.jpg")
    private Long idProduct;

    @Column(name = "url_product")
    @NotBlank(message = "El campo url no puede ser vacio")
    @Schema(description = "Url del producto", example = "/sillagamer.html")
    private String url;

    @Column(name = "alt_product")
    @NotBlank(message = "El campo alt no puede ser vacio")
    @Schema(description = "Alt del producto", example = "Silla acolchonada para gamer")
    private String alt;

    @Column(name = "isprimary_product")
    @NotBlank(message = "El campo no puede ser vacio")
    @Schema(description = "IsPrimary del producto", example = "seleccion de productos X")
    private boolean isPrimary;

    @OneToMany(mappedBy = "product-images", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("product-images")
    private List<Producto> productos = new ArrayList<>();
}
