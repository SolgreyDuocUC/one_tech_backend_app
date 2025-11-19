package com.duocuc.one_tech.services.Role;

import com.duocuc.one_tech.dto.role.RoleDTO;
import com.duocuc.one_tech.exceptions.RoleException;
import com.duocuc.one_tech.models.Role;
import com.duocuc.one_tech.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream()
                .map(role -> new RoleDTO(role.getName(), role.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public Role save(RoleDTO roleDTO) {
        Role newRole = new Role();

        newRole.setName(roleDTO.getName());
        newRole.setDescription(roleDTO.getDescription());

        return roleRepository.save(newRole);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleException("Role con nombre " + name + " no encontrado"));
    }
}
