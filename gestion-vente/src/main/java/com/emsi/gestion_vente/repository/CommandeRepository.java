package com.emsi.gestion_vente.repository;

import com.emsi.gestion_vente.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    
    // Trouver les commandes par client
    List<Commande> findByClient(String client);
    
    // Trouver les commandes par produit
    List<Commande> findByCodePdt(Integer codePdt);
}