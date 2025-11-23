package com.duocuc.one_tech.dto.user;

import lombok.*;
import java.time.OffsetDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String rut;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean isActive;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}

