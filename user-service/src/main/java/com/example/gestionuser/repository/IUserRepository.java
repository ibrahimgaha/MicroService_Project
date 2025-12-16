package com.example.gestionuser.repository;

import com.example.gestionuser.entity.User;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {
}

