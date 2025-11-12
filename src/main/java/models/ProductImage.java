package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCT_IMAGES")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product_image")
    private Long id;

    @Column(name = "url_image", length = 512, nullable = false)
    private String url;

    @Column(name = "alt_image", length = 120)
    private String alt;

    @Column(name = "is_main")
    private Boolean isMain;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    @JsonBackReference("product-images")
    private Product product;
}

