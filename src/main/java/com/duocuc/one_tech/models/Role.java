package com.duocuc.one_tech.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLES")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Long idRole;

    @Column(name = "code_roles", length = 30, nullable = false, unique = true)
    private String codeRoles;

    @Column(name = "name_role", length = 40, nullable = false, unique = true)
    private String name;

    @Column(name = "desc_role", length = 120)
    private String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();

    public Long getId() {
        return idRole;
    }

    public String getCode() {
        return this.codeRoles;
    }
}
