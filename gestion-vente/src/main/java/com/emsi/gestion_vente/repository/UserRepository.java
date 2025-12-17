package com.emsi.gestion_vente.repository;

import com.emsi.gestion_vente.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    
    // Trouver un utilisateur par login
    Optional<Users> findByLogin(String login);
    
    // Vérifier si un login existe
    boolean existsByLogin(String login);
}