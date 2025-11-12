package models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.*;

@Entity
@Table(name = "POSTS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private Long id;

    @Column(name = "ttl_post", length = 200, nullable = false)
    @NotBlank
    private String title;

    @Column(name = "slug_post", length = 200, nullable = false)
    @NotBlank
    private String slug;

    @Column(name = "excerpt_post", length = 600)
    private  String excerpt;

    @Column(name = "content_post", length = 8000)
    private String content;

    @Column(name = "cover_image_url_post", length = 512)
    private String coverImageUrl;

    @Column(name = "published_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime publishedAt;

    @Column(name = "is_published")
    private Boolean isPublished;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("post-comments")
    private List<PostComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("post-posttags")
    private Set<com.yourapp.model.PostTag> postTags = new HashSet<>();

    public void addTag(Tag tag, User user) {
        com.yourapp.model.PostTag pt = new com.yourapp.model.PostTag(new PostTagId(this.id, tag.getId(), user != null ? user.getId() : null), this, tag, user);
        postTags.add(pt);
        tag.getPostTags().add(pt);
    }
}
