package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity  @Table(name = "CATEGORIES")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long id;

    @Column(name = "name_category", length = 80, nullable = false)
    private String name;

    @Column(name = "slug_category", length = 80, nullable = false)
    private String slug;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "category")
    @JsonBackReference("category-products")
    private List<Product> products = new ArrayList<>();
}
