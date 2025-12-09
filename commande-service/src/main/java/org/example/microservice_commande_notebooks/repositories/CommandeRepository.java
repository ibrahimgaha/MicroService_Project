package org.example.microservice_commande_notebooks.repositories;

import org.example.microservice_commande_notebooks.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
