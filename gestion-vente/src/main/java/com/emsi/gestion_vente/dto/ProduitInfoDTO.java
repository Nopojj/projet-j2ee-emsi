package com.emsi.gestion_vente.dto;

/**
 * DTO pour afficher les informations complètes d'un produit dans la liste
 * Combine stock et commercial
 */
public class ProduitInfoDTO {

    private Integer codePdt;
    private String nomPdt;       // Nom du produit (depuis commercial)
    private Double prixPdt;      // ← Double, pas Integer !
    private Integer qtePdt;      // Quantité en stock (depuis stock)

    // Constructeur complet
    public ProduitInfoDTO(Integer codePdt, String nomPdt, Double prixPdt, Integer qtePdt) {
        this.codePdt = codePdt;
        this.nomPdt = nomPdt;
        this.prixPdt = prixPdt;
        this.qtePdt = qtePdt;
    }

    // Constructeur vide (nécessaire pour Jackson)
    public ProduitInfoDTO() {}

    // Getters
    public Integer getCodePdt() {
        return codePdt;
    }

    public String getNomPdt() {
        return nomPdt;
    }

    public Double getPrixPdt() {
        return prixPdt;
    }

    public Integer getQtePdt() {
        return qtePdt;
    }

    // Setters
    public void setCodePdt(Integer codePdt) {
        this.codePdt = codePdt;
    }

    public void setNomPdt(String nomPdt) {
        this.nomPdt = nomPdt;
    }

    public void setPrixPdt(Double prixPdt) {
        this.prixPdt = prixPdt;
    }

    public void setQtePdt(Integer qtePdt) {
        this.qtePdt = qtePdt;
    }
}