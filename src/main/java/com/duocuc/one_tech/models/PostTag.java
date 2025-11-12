package com.duocuc.one_tech.models;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "POST_TAGS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PostTag {

    @EmbeddedId
    private PostTagId id;

    @ManyToOne(optional = false)
    @MapsId("idPost")
    @JoinColumn(name = "id_post", nullable = false)
    @JsonBackReference("post-posttags")
    private Post post;

    @ManyToOne(optional = false)
    @MapsId("idTag")
    @JoinColumn(name = "id_tag", nullable = false)
    @JsonBackReference("tag-posttags")
    private Tag tag;

    @ManyToOne(optional = true)
    @MapsId("idUsers")
    @JoinColumn(name = "id_users")
    private User user;

    public PostTag(PostTagId id, Post post, Tag tag, User user) {
        this.id = id;
        this.post = post;
        this.tag = tag;
        this.user = user;
    }
}
