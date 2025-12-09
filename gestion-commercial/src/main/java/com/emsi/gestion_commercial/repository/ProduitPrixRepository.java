package com.emsi.gestion_commercial.repository;

import com.emsi.gestion_commercial.entity.ProduitPrix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProduitPrixRepository extends JpaRepository<ProduitPrix, Long> {
}