package com.duocuc.one_tech.services;

import com.duocuc.one_tech.dto.contactMessage.dto.ContactMessageRequest;
import com.duocuc.one_tech.dto.contactMessage.ContactMessageDTO;

import java.util.List;

public interface ContactService {

    ContactMessageDTO createMessage(ContactMessageRequest request);

    List<ContactMessageDTO> getAllMessages();

    ContactMessageDTO getMessageById(Long id);

    ContactMessageDTO updateStatus(Long id, String status);

    void deleteMessage(Long id);
}
