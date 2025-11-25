package com.duocuc.one_tech.services.Role;

import com.duocuc.one_tech.dto.role.roleDTO;
import com.duocuc.one_tech.exceptions.RoleException;
import com.duocuc.one_tech.models.Role;
import com.duocuc.one_tech.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public List<roleDTO> findAll() {
        return roleRepository.findAll().stream()
                .map(role -> new roleDTO(
                        role.getId(),
                        role.getName(),
                        role.getDescription()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Role save(roleDTO roleDTO) {
        if (roleDTO == null) {
            throw new RoleException("El objeto roleDTO no puede ser nulo");
        }

        Role newRole = new Role();
        newRole.setName(roleDTO.getName());
        newRole.setDescription(roleDTO.getDescription());

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
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleException(
                        "Role con nombre '" + name + "' no encontrado"
                ));
    }

    @Override
    public Role findById(Long idRole) {
        return roleRepository.findById(idRole)
                .orElseThrow(() -> new RoleException(
                        "Role con ID " + idRole + " no encontrado"
                ));
    }

    @Override
    public Role save(Role role) {
        if (role == null) {
            throw new RoleException("El role no puede ser nulo");
        }
        return roleRepository.save(role);
    }

    @Override
    public void delete(Long idRole) {
        Role role = roleRepository.findById(idRole)
                .orElseThrow(() -> new RoleException(
                        "No se puede eliminar. Role con ID " + idRole + " no existe"
                ));

        roleRepository.delete(role);
    }
}

