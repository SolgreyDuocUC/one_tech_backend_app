package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TAGS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tag")
    private Long id;

    @Column(name = "name_tag", length = 20, nullable = false)
    @NotBlank
    private String name;

    @Column(name = "slug_tag", length = 20, nullable = false)
    @NotBlank
    private String slug;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("tag-posttags")
    private Set<com.yourapp.model.PostTag> postTags = new HashSet<>();
}
