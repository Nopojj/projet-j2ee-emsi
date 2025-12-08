package com.emsi.gestion_stock.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Produits_Stock")
public class ProduitStock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_stock")  
    private Long codeStock;
    
    @Column(name = "code_pdt")    
    private Long codePdt;
    
    @Column(name = "qte_pdt")     
    private Integer qtePdt;
    
    // Constructeurs
    public ProduitStock() {}
    
    public ProduitStock(Long codePdt, Integer qtePdt) {
        this.codePdt = codePdt;
        this.qtePdt = qtePdt;
    }
    
    // Getters et Setters
    public Long getCodeStock() { return codeStock; }
    public void setCodeStock(Long codeStock) { this.codeStock = codeStock; }
    
    public Long getCodePdt() { return codePdt; }
    public void setCodePdt(Long codePdt) { this.codePdt = codePdt; }
    
    public Integer getQtePdt() { return qtePdt; }
    public void setQtePdt(Integer qtePdt) { this.qtePdt = qtePdt; }
}