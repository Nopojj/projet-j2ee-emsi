package com.emsi.gestion_vente.dto;

/**
 * DTO pour récupérer les infos depuis gestion_commercial
 */
public class ProduitPrixDTO {
    
    private Integer codePdt;
    private String nomPdt;
    private String descPdt;
    private Double prixPdt;
    
    // Constructeurs
    public ProduitPrixDTO() {}
    
    public ProduitPrixDTO(Integer codePdt, String nomPdt, String descPdt, Double prixPdt) {
        this.codePdt = codePdt;
        this.nomPdt = nomPdt;
        this.descPdt = descPdt;
        this.prixPdt = prixPdt;
    }
    
    // Getters et Setters
    public Integer getCodePdt() {
        return codePdt;
    }
    
    public void setCodePdt(Integer codePdt) {
        this.codePdt = codePdt;
    }
    
    public String getNomPdt() {
        return nomPdt;
    }
    
    public void setNomPdt(String nomPdt) {
        this.nomPdt = nomPdt;
    }
    
    public String getDescPdt() {
        return descPdt;
    }
    
    public void setDescPdt(String descPdt) {
        this.descPdt = descPdt;
    }
    
    public Double getPrixPdt() {
        return prixPdt;
    }
    
    public void setPrixPdt(Double prixPdt) {
        this.prixPdt = prixPdt;
    }
}