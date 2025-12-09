package com.emsi.gestion_commercial.controller;

import com.emsi.gestion_commercial.entity.Commande;
import com.emsi.gestion_commercial.entity.ProduitPrix;
import com.emsi.gestion_commercial.repository.CommandeRepository;
import com.emsi.gestion_commercial.repository.ProduitPrixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Permet les appels depuis d'autres applications
public class CommercialController {

    @Autowired
    private ProduitPrixRepository produitPrixRepository;

    @Autowired  // ⭐ AJOUTEZ CECI - Injection du CommandeRepository
    private CommandeRepository commandeRepository;

    // Endpoint 1: Lister tous les produits (demandé dans le PDF)
    @GetMapping("/produits")
    public List<ProduitPrix> getAllProduits() {
        return produitPrixRepository.findAll();
    }

    // Endpoint 2: Ajouter une commande (demandé dans le PDF)
    @PostMapping("/commandes")
    public ResponseEntity<Commande> addCommande(@RequestBody CommandeRequest request) {
        // Créer une nouvelle commande à partir de la requête
        Commande commande = new Commande();
        commande.setCodeCmd(request.getCodeCmd());
        commande.setClient(request.getClient());
        commande.setCodePdt(request.getCodePdt());
        commande.setQteCmd(request.getQteCmd());
        commande.setDateCmd(LocalDate.parse(request.getDateCmd()));
        
        // Sauvegarder dans la base de données
        Commande savedCommande = commandeRepository.save(commande);
        
        return ResponseEntity.ok(savedCommande);
    }

    // Endpoint bonus: Ajouter un produit (pour tester)
    @PostMapping("/produits")
    public ProduitPrix addProduit(@RequestBody ProduitPrix produit) {
        return produitPrixRepository.save(produit);
    }

    // Endpoint bonus: Lister toutes les commandes (pour vérifier)
    @GetMapping("/commandes")
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    // Classe interne pour recevoir les données de commande
    public static class CommandeRequest {
        private Integer codeCmd;      // ⚠️ Changé de Long à Integer
        private String client;
        private Integer codePdt;      // ⚠️ Changé de Long à Integer
        private Integer qteCmd;
        private String dateCmd;       // Format: "2025-12-04"

        // Getters et Setters
        public Integer getCodeCmd() { return codeCmd; }
        public void setCodeCmd(Integer codeCmd) { this.codeCmd = codeCmd; }

        public String getClient() { return client; }
        public void setClient(String client) { this.client = client; }

        public Integer getCodePdt() { return codePdt; }
        public void setCodePdt(Integer codePdt) { this.codePdt = codePdt; }

        public Integer getQteCmd() { return qteCmd; }
        public void setQteCmd(Integer qteCmd) { this.qteCmd = qteCmd; }

        public String getDateCmd() { return dateCmd; }
        public void setDateCmd(String dateCmd) { this.dateCmd = dateCmd; }
    }
}