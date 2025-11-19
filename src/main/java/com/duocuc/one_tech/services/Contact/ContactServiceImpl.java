package com.duocuc.one_tech.services.Contact;

import com.duocuc.one_tech.dto.contactMessage.ContactMessageDTO;
import com.duocuc.one_tech.dto.contactMessage.dto.ContactMessageMapper;
import com.duocuc.one_tech.dto.contactMessage.dto.ContactMessageRequest;
import com.duocuc.one_tech.exceptions.NotFoundException;
import com.duocuc.one_tech.models.ContactMessage;
import com.duocuc.one_tech.models.User;
import com.duocuc.one_tech.repositories.ContactMessageRepository;
import com.duocuc.one_tech.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private final ContactMessageRepository repository;
    private final UserRepository userRepository;

    public ContactServiceImpl(ContactMessageRepository repository,
                              UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public ContactMessageDTO createMessage(ContactMessageRequest request) {

        ContactMessage msg = new ContactMessage();

        msg.setNameMessage(request.nameMessage());
        msg.setEmail(request.email());
        msg.setPhone(request.phone());
        msg.setMessage(request.message());
        msg.setVia(request.via());
        msg.setStatus(request.status() != null ? request.status() : "pendiente");
        msg.setCreatedAt(OffsetDateTime.now());

        if (request.userId() != null) {
            User user = userRepository.findById(request.userId())
                    .orElseThrow(() -> new NotFoundException("Usuario no encontrado con id " + request.userId()));
            msg.setUser(user);
        }

        ContactMessage saved = repository.save(msg);
        return ContactMessageMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactMessageDTO> getAllMessages() {
        return repository.findAll().stream()
                .map(ContactMessageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ContactMessageDTO getMessageById(Long id) {
        ContactMessage msg = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mensaje no encontrado con id " + id));

        return ContactMessageMapper.toDto(msg);
    }

    @Override
    public ContactMessageDTO updateStatus(Long id, String status) {
        ContactMessage msg = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mensaje no encontrado con id " + id));

        msg.setStatus(status);

        return ContactMessageMapper.toDto(repository.save(msg));
    }

    @Override
    public void deleteMessage(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Mensaje no encontrado con id " + id);
        }
        repository.deleteById(id);
    }
}
