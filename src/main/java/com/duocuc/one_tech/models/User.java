package com.duocuc.one_tech.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"carts", "reviews", "userRoles"})
@Schema(description = "Entidad que representa un usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_users")
    private Long id;

    @Column(name = "rut_users", length = 12, nullable = false)
    private String rut;

    @Column(name = "first_name_users", length = 60, nullable = false)
    private String firstName;

    @Column(name = "last_name_users", length = 60, nullable = false)
    private String lastName;

    @Column(name = "email_users", length = 120, nullable = false, unique = true)
    private String email;

    @Column(name = "phone_users", length = 20)
    private String phone;

    @Column(name = "password_hash_users", length = 255, nullable = false)
    private String passwordHash;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();

    public @NotNull(message = "El ID del usuario no puede ser nulo") Long getIdUsers() {
        return this.id;
    }

    public String getName() {
        return  this.firstName + " " + this.lastName;
    }
}
