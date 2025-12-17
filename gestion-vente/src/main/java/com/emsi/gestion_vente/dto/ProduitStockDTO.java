package com.emsi.gestion_vente.dto;

/**
 * DTO pour récupérer les infos depuis gestion_stock
 */
public class ProduitStockDTO {
    
    private Integer codeStock;
    private Integer codePdt;
    private Integer qtePdt;
    
    // Constructeurs
    public ProduitStockDTO() {}
    
    public ProduitStockDTO(Integer codeStock, Integer codePdt, Integer qtePdt) {
        this.codeStock = codeStock;
        this.codePdt = codePdt;
        this.qtePdt = qtePdt;
    }
    
    // Getters et Setters
    public Integer getCodeStock() {
        return codeStock;
    }
    
    public void setCodeStock(Integer codeStock) {
        this.codeStock = codeStock;
    }
    
    public Integer getCodePdt() {
        return codePdt;
    }
    
    public void setCodePdt(Integer codePdt) {
        this.codePdt = codePdt;
    }
    
    public Integer getQtePdt() {
        return qtePdt;
    }
    
    public void setQtePdt(Integer qtePdt) {
        this.qtePdt = qtePdt;
    }
}