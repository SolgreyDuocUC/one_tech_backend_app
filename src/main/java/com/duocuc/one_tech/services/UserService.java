package com.duocuc.one_tech.services;

import com.duocuc.one_tech.dtos.ProductViewUserDTO;
import com.duocuc.one_tech.dtos.UserCreationDTO;
import com.duocuc.one_tech.models.Product;
import com.duocuc.one_tech.models.User;

import java.util.List;

public interface UserService {
    List<Product> findAll();
    User findById(Long id);
    User save (User user);
    void deleteById(Long id);
    User updateById(Long id, User user);
    User findByCorreo(String correo);
    User findByRun (String run);
    List <ProductViewUserDTO> findProductbyUserId(Long id);
}
