package com.emsi.gestion_commercial.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Produits_Prix")
public class ProduitPrix {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codePdt;
    
    @Column(name = "nomPdt", length = 20)
    private String nomPdt;
    
    @Column(name = "descPdt", length = 200)
    private String descPdt;
    
    @Column(name = "prixPdt")
    private Double prixPdt;
    
    // Constructeurs
    public ProduitPrix() {}
    
    public ProduitPrix(String nomPdt, String descPdt, Double prixPdt) {
        this.nomPdt = nomPdt;
        this.descPdt = descPdt;
        this.prixPdt = prixPdt;
    }
    
    // Getters et Setters
    public Long getCodePdt() { return codePdt; }
    public void setCodePdt(Long codePdt) { this.codePdt = codePdt; }
    
    public String getNomPdt() { return nomPdt; }
    public void setNomPdt(String nomPdt) { this.nomPdt = nomPdt; }
    
    public String getDescPdt() { return descPdt; }
    public void setDescPdt(String descPdt) { this.descPdt = descPdt; }
    
    public Double getPrixPdt() { return prixPdt; }
    public void setPrixPdt(Double prixPdt) { this.prixPdt = prixPdt; }
}