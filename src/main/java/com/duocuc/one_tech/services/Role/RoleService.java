package com.duocuc.one_tech.services.Role;

import com.duocuc.one_tech.dto.role.RoleDTO;

import java.util.List;

public interface RoleService {

    List<RoleDTO> listarRoles();

    RoleDTO obtenerPorId(Long id);

    RoleDTO obtenerPorNombre(String name);

    RoleDTO crearRole(RoleDTO dto);

    RoleDTO actualizarRole(Long id, RoleDTO dto);

    void eliminarRole(Long id);
}
