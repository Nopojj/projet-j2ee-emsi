package com.emsi.gestioncommercial.repository;

import com.emsi.gestioncommercial.entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    // Pas besoin d'ajouter de méthodes
    // Spring Data JPA génère automatiquement les méthodes de base :
    // - save()
    // - findAll()
    // - findById()
    // - delete()
    // etc.
}