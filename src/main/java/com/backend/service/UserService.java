package com.backend.service;

import com.backend.entities.User;

import java.util.List;

public interface UserService {
    Long createUser(User user);
    List<User> getAllUsers();
    Long editUser(User user, Long id);
    void deleteUser(Long id);
    User getOneUser(Long id);
}
