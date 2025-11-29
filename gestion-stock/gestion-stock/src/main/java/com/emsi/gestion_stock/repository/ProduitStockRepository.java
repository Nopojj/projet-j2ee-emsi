package com.emsi.gestion_stock.repository;

import com.emsi.gestion_stock.entity.ProduitStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitStockRepository extends JpaRepository<ProduitStock, Long> {
    
    @Query("SELECT p FROM ProduitStock p WHERE p.codePdt = :codePdt")
    ProduitStock findByCodePdt(@Param("codePdt") Long codePdt);
}