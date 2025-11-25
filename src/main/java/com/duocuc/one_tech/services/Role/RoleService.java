package com.duocuc.one_tech.services.Role;

import com.duocuc.one_tech.dto.role.RoleDTO;
import com.duocuc.one_tech.dto.role.roleDTO;
import com.duocuc.one_tech.models.Role;

import java.util.List;

public interface RoleService {

    List<RoleDTO> listarRoles();

    RoleDTO obtenerPorId(Long id);

    RoleDTO obtenerPorNombre(String name);

    RoleDTO crearRole(RoleDTO dto);

    RoleDTO actualizarRole(Long id, RoleDTO dto);

    void eliminarRole(Long id);
    List<roleDTO> findAll();

    Role save(roleDTO roleDTO);

    Role findByName(String name);

    Role findById(Long idRole);

    Role save(Role role);

    void delete(Long idRole);
}
