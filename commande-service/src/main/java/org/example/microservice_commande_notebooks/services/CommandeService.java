package org.example.microservice_commande_notebooks.services;

import org.example.microservice_commande_notebooks.entities.Commande;

import java.util.List;
import java.util.Optional;

public interface CommandeService {
    Commande createCommande(Commande commande);
    List<Commande> getAllCommandes();
    Optional<Commande> getCommandeById(Long id);
    Commande updateCommande(Long id, Commande commande);
    void deleteCommande(Long id);
}
