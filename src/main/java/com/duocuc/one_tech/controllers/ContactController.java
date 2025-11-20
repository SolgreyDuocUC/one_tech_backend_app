package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.contactMessage.ContactMessageDTO;
import com.duocuc.one_tech.dto.contactMessage.dto.ContactMessageRequest;
import com.duocuc.one_tech.services.Contact.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // Enviar mensaje (desde el formulario)
    @PostMapping("/messages")
    public ResponseEntity<ContactMessageDTO> create(@RequestBody ContactMessageRequest request) {
        return ResponseEntity.ok(contactService.createMessage(request));
    }

    // Listar todos los mensajes
    @GetMapping("/messages")
    public ResponseEntity<List<ContactMessageDTO>> getAll() {
        return ResponseEntity.ok(contactService.getAllMessages());
    }

    // Ver mensaje específico
    @GetMapping("/messages/{id}")
    public ResponseEntity<ContactMessageDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.getMessageById(id));
    }

    // Cambiar estado del mensaje
    @PatchMapping("/messages/{id}/status")
    public ResponseEntity<ContactMessageDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return ResponseEntity.ok(contactService.updateStatus(id, status));
    }

    // Eliminar mensaje
    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contactService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
