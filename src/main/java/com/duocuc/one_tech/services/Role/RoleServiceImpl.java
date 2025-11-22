package com.duocuc.one_tech.services.Role;

import com.duocuc.one_tech.dto.role.roleDTO;
import com.duocuc.one_tech.exceptions.RoleException;
import com.duocuc.one_tech.models.Role;
import com.duocuc.one_tech.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
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

        return roleRepository.save(newRole);
    }

    @Override
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
