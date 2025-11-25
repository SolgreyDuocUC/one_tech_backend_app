package com.duocuc.one_tech.services.User;

import com.duocuc.one_tech.dto.user.CreateUserDTO;
import com.duocuc.one_tech.dto.user.UpdateUserDTO;
import com.duocuc.one_tech.dto.user.UserDTO;
import com.duocuc.one_tech.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO createUser(CreateUserDTO dto);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UpdateUserDTO dto);
    void deleteUser(Long id);

    Optional<User> findById(Long id);
}

