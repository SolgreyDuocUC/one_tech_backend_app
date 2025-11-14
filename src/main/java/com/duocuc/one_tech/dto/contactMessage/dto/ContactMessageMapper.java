package com.duocuc.one_tech.dto.contactMessage.dto;

import com.duocuc.one_tech.dto.contactMessage.ContactMessageDTO;
import com.duocuc.one_tech.models.ContactMessage;
import com.duocuc.one_tech.models.User;

public class ContactMessageMapper {

    public static ContactMessageDTO toDto(ContactMessage entity) {
        if (entity == null) return null;

        Long userId = entity.getUser() != null ? entity.getUser().getId() : null;

        return new ContactMessageDTO(
                entity.getId(),
                entity.getNameMessage(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getMessage(),
                entity.getVia(),
                entity.getStatus(),
                entity.getCreatedAt(),
                userId
        );
    }
}
