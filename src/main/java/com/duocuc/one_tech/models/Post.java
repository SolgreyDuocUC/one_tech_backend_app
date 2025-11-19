package com.duocuc.one_tech.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "POSTS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private Long id;

    @Column(name = "ttl_post", length = 200, nullable = false)
    @NotBlank(message = "El título no puede estar vacío.")
    @Size(min = 1, max = 200)
    private String title;

    @Column(name = "slug_post", length = 200, nullable = false, unique = true)
    @NotBlank(message = "El slug no puede estar vacío.")
    @Size(min = 1, max = 200)
    private String slug;

    @Column(name = "excerpt_post", length = 600)
    @Size(max = 600)
    private  String excerpt;

    @Column(name = "content_post", length = 8000)
    @Size(max = 8000)
    private String content;

    @Column(name = "cover_image_url_post", length = 512)
    @Size(max = 512)
    private String coverImageUrl;

    @Column(name = "published_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime publishedAt;

    @Column(name = "is_published")
    @NotNull
    private Boolean isPublished = Boolean.FALSE;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("post-comments")
    private List<PostComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("post-posttags")
    private Set<PostTag> postTags = new HashSet<>();

    public void addTag(Tag tag, User user) {
        PostTag pt = new PostTag();
        pt.setPost(this);
        pt.setTag(tag);
        pt.setUser(user);

        postTags.add(pt);
        tag.getPostTags().add(pt);
    }
}