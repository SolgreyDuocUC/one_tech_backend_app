package models;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "USER_ROLES")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserRole {
    @EmbeddedId
    private UserRoleId roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUsers")
    @JoinColumn(name = "id_users", nullable = false)
    private  User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idRole")
    @JoinColumn(name = "id_role", nullable = false)
    private  Role role;

    @Column(name = "granted_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime grantedAt;

}
