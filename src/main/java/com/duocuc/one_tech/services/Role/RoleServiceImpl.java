package com.duocuc.one_tech.services.Role;

import com.duocuc.one_tech.dto.role.RoleDTO;
import com.duocuc.one_tech.exceptions.RoleException;
import com.duocuc.one_tech.models.Role;
import com.duocuc.one_tech.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    private RoleDTO toDTO(Role role) {
        return new RoleDTO(role.getName(), role.getDescription());
    }

    @Override
    public List<RoleDTO> listarRoles() {
        return roleRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public RoleDTO obtenerPorId(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        return toDTO(role);
    }

    @Override
    public RoleDTO obtenerPorNombre(String name) {
        Role role = roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        return toDTO(role);
    }

    @Override
    public RoleDTO crearRole(RoleDTO dto) {

        roleRepository.findByName(dto.getName())
                .ifPresent(r -> {
                    throw new RuntimeException("El rol ya existe");
                });

        Role role = new Role();
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());

        Role saved = roleRepository.save(role);
        return toDTO(saved);
    }

    @Override
    public RoleDTO actualizarRole(Long id, RoleDTO dto) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        role.setName(dto.getName());
        role.setDescription(dto.getDescription());

        Role updated = roleRepository.save(role);
        return toDTO(updated);
    }

    @Override
    public void eliminarRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Rol no encontrado");
        }
        roleRepository.deleteById(id);
    }
}

