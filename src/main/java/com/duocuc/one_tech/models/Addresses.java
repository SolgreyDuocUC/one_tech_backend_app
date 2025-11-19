package com.duocuc.one_tech.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "ADDRESSES")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @ToString
@Schema(description = "Entidad que representa direccion")
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long id;

    @Column(name = "line1_address", length = 120, nullable = false)
    @NotBlank
    private String line1Address;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "creates_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createsAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_users", nullable = false)
    @JsonBackReference("user-addresses")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_commune", nullable = false)
    @JsonBackReference("commune-addresses")
    private Communes commune;
}