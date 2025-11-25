package com.duocuc.one_tech.dto.category;

public record CategoryDTO(
        Long id,
        String name,
        String slug,
        Boolean isActive,
        Long parentId
) {}
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDTO {
    private Long id;
    private String name;
    private String slug;
    private Boolean isActive;
    private Long parentId; // id de la categoría padre, si existe
    private List<CategoryDTO> childCategories; // opcional para respuesta jerárquica
}
