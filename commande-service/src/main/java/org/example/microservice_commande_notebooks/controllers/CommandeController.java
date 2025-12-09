package org.example.microservice_commande_notebooks.controllers;

import org.example.microservice_commande_notebooks.entities.Commande;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.microservice_commande_notebooks.services.CommandeServiceImpl;

import java.util.List;
@RestController
@RequestMapping("/commandes")
public class CommandeController {

    private final CommandeServiceImpl commandeService;

    public CommandeController(CommandeServiceImpl commandeService) {
        this.commandeService = commandeService;
    }

    @PostMapping
    public ResponseEntity<Commande> create(@RequestBody Commande commande) {
        return ResponseEntity.ok(commandeService.createCommande(commande));
    }

    @GetMapping
    public ResponseEntity<List<Commande>> getAll() {
        return ResponseEntity.ok(commandeService.getAllCommandes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getById(@PathVariable Long id) {
        return commandeService.getCommandeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> update(@PathVariable Long id, @RequestBody Commande commande) {
        return ResponseEntity.ok(commandeService.updateCommande(id, commande));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }
}
