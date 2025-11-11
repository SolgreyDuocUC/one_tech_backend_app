package models;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Embeddable
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
public class PostTagId implements Serializable {
    private Long idPost;
    private Long idTag;
    private Long idUsers;
}
