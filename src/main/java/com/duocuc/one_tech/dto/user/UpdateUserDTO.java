package com.duocuc.one_tech.dto.user;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateUserDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private Boolean isActive;
}