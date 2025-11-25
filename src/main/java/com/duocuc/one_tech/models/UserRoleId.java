package com.duocuc.one_tech.models;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserRoleId implements Serializable {

    @Column(name = "id_users")
    private Long idUsers;

    @Column(name = "id_role")
    private Long idRoles;
}