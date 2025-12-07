package com.emsi.gestioncommercial.repository;

import com.emsi.gestioncommercial.entity.ProduitPrix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProduitPrixRepository extends JpaRepository<ProduitPrix, Long> {
}