package com.example.gestionuser.service;

import com.example.gestionuser.entity.User;

import java.util.List;
import java.util.Optional;

public interface IGestionUser {
    User addUser(User u);
    List<User> getAllUsers();            // READ ALL
    Optional<User> getUserById(int id); // READ BY ID
    User updateUser(int id, User u);    // UPDATE
    void deleteUser(int id);            // DELETE
}