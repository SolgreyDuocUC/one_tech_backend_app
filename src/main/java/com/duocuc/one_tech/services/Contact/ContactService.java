package com.duocuc.one_tech.services.Contact;

import com.duocuc.one_tech.dto.contactMessage.dto.ContactMessageRequest;
import com.duocuc.one_tech.dto.contactMessage.ContactMessageDTO;
import com.duocuc.one_tech.dto.contactMessage.dto.ContactUpdateDTO;

import java.util.List;

public interface ContactService {

    ContactMessageDTO createMessage(ContactMessageRequest request);

    List<ContactMessageDTO> getAllMessages();

    ContactMessageDTO getMessageById(Long id);

    ContactMessageDTO updateStatus(Long id, String status);

    ContactMessageDTO actualizarMensaje(Long id, ContactUpdateDTO dto);



    void deleteMessage(Long id);
}
