package com.duocuc.one_tech.dto.post;

import com.duocuc.one_tech.dto.post.TagDTO;
import com.duocuc.one_tech.models.Tag;

public class TagMapper {

    public static TagDTO toDto(Tag tag) {
        if (tag == null) return null;

        return new TagDTO(
                tag.getId(),
                tag.getName(),  // coincide perfecto con tu entidad
                tag.getSlug()
        );
    }
}
