package com.duocuc.one_tech.services.Role;

import com.duocuc.one_tech.dto.role.RoleDTO;
import com.duocuc.one_tech.models.Role;

import java.util.List;

public interface RoleService {

    List<RoleDTO> findAll();
    Role save(RoleDTO roleDTO);
    Role findByName(String name);
}

