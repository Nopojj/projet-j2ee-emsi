package com.emsi.gestion_stock.controller;

import com.emsi.gestion_stock.dto.SoustractionRequest;
import com.emsi.gestion_stock.entity.ProduitStock;
import com.emsi.gestion_stock.repository.ProduitStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StockController {
    
    @Autowired
    private ProduitStockRepository produitStockRepository;
    
    // Endpoint 1: Lister tous les stocks - DÉJÀ BON !
    @GetMapping("/stocks")
    public List<ProduitStock> getAllStocks() {
        return produitStockRepository.findAll();
    }
    
    // Endpoint 2: Soustraire du stock - NOUVEAU AVEC POST !
    @PostMapping("/stocks/soustraire")
    public ResponseEntity<?> soustraireStock(@RequestBody SoustractionRequest request) {
        Long codePdt = request.getCodePdt();
        Integer qteCmd = request.getQteCmd();
        
        // Trouve le produit par codePdt
        ProduitStock produitStock = produitStockRepository.findByCodePdt(codePdt);
        
        if (produitStock == null) {
            return ResponseEntity.status(404).body("Produit non trouvé");
        }
        
        // Vérifie si le stock est suffisant
        if (produitStock.getQtePdt() < qteCmd) {
            return ResponseEntity.status(400).body("Stock insuffisant");
        }
        
        // Soustrait la quantité
        produitStock.setQtePdt(produitStock.getQtePdt() - qteCmd);
        produitStockRepository.save(produitStock);
        
        return ResponseEntity.ok("Stock mis à jour avec succès");
    }
}