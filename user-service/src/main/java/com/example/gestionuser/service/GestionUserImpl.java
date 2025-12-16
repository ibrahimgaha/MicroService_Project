package com.example.gestionuser.service;

import com.example.gestionuser.entity.User;
import com.example.gestionuser.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestionUserImpl implements IGestionUser {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public User addUser(User u) {
        return userRepository.save(u);
    }
    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(int id, User u) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setNom(u.getNom());
                    existingUser.setPrenom(u.getPrenom());
                    existingUser.setEmail(u.getEmail());
                    existingUser.setTelephone(u.getTelephone());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID : " + id));
    }

    @Override
    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur introuvable avec l'ID : " + id);
        }
        userRepository.deleteById(id);
    }
}
