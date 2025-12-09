package org.example.microservice_commande_notebooks.services;

import org.example.microservice_commande_notebooks.entities.Commande;
import org.springframework.stereotype.Service;
import org.example.microservice_commande_notebooks.repositories.CommandeRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CommandeServiceImpl {
    private final CommandeRepository commandeRepository;

    public CommandeServiceImpl(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }


    public Commande createCommande(Commande commande) {
        return commandeRepository.save(commande);
    }


    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }


    public Optional<Commande> getCommandeById(Long id) {
        return commandeRepository.findById(id);
    }


    public Commande updateCommande(Long id, Commande commande) {
        return commandeRepository.findById(id).map(existing -> {
            existing.setClientName(commande.getClientName());
            existing.setProductName(commande.getProductName());
            existing.setQuantity(commande.getQuantity());
            existing.setPrice(commande.getPrice());
            existing.setOrderDate(commande.getOrderDate());
            existing.setStatus(commande.getStatus());
            return commandeRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Commande not found"));
    }


    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }
}


