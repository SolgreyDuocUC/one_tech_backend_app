package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity @Table(name = "REVIEWS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review")
    private Long id;

    @Column(name = "rating_review", nullable = false)
    private Integer rating;

    @Column(name = "title_review", length = 120)
    private String title;

    @Column(name = "content_review", length = 1000)
    private String content;


    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @Column(name = "is_visible")
    private Boolean isVisible;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    @JsonBackReference("product-reviews")
    private Product product;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_users", nullable = false)
    private User author;
}

