package com.duocuc.one_tech.services.User;

import com.duocuc.one_tech.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);
    List<User> findAll();
    User save(User user);
    void delete(Long id);
}