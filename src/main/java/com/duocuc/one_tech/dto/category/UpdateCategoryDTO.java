package com.duocuc.one_tech.dto.category;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateCategoryDTO {
    private String name;
    private String slug;
    private Boolean isActive;
    private Long parentId;
}
