package com.emsi.gestion_stock.dto;

public class SoustractionRequest {
    private Long codePdt;
    private Integer qteCmd;
    
    // Constructeurs
    public SoustractionRequest() {}
    
    public SoustractionRequest(Long codePdt, Integer qteCmd) {
        this.codePdt = codePdt;
        this.qteCmd = qteCmd;
    }
    
    // Getters et Setters
    public Long getCodePdt() { return codePdt; }
    public void setCodePdt(Long codePdt) { this.codePdt = codePdt; }
    
    public Integer getQteCmd() { return qteCmd; }
    public void setQteCmd(Integer qteCmd) { this.qteCmd = qteCmd; }
}