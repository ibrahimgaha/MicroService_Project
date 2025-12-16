package com.example.gestionuser.controller;

import com.example.gestionuser.entity.User;
import com.example.gestionuser.service.IGestionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "*") // si tu veux tester avec Angular ou Postman
public class    UserController {

    private final IGestionUser serviceUser;

    @Autowired
    public UserController(IGestionUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    // CREATE
    @PostMapping("/addUser")
    public User addUser(@RequestBody User u) {
        return serviceUser.addUser(u);
    }

    // READ ALL
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return serviceUser.getAllUsers();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return serviceUser.getUserById(id);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User u) {
        return serviceUser.updateUser(id, u);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        serviceUser.deleteUser(id);
        return "Utilisateur supprimé avec succès (ID = " + id + ")";
    }
}
