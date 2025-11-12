package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.OffsetDateTime;

@Entity
@Table(name = "POST_COMMENTS")
@Getter  @Setter @NoArgsConstructor @AllArgsConstructor
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Long id;

    @Column(name = "content_comment", length = 1000, nullable = false)
    @NotBlank
    private String content;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH ZONE")
    private OffsetDateTime createdAt;

    @Column(name = "is_visible")
    private Boolean isVisible;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_post", nullable = false)
    @JsonBackReference("post-comments")
    private Post post;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_users", nullable = false)
    private User author;
}
