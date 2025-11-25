package com.duocuc.one_tech.dto.user;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUserDTO {
    private String rut;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private Boolean isActive;
}
