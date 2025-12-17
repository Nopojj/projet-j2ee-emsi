package com.emsi.gestion_vente.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "commandes")
public class Commande {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codeCmd;
    
    @Column(length = 20)
    private String client;
    
    private Integer codePdt;
    
    private Integer qteCmd;
    
    private LocalDate dateCmd;
    
    // Constructeurs
    public Commande() {
        this.dateCmd = LocalDate.now();
    }
    
    public Commande(String client, Integer codePdt, Integer qteCmd) {
        this.client = client;
        this.codePdt = codePdt;
        this.qteCmd = qteCmd;
        this.dateCmd = LocalDate.now();
    }
    
    // Getters et Setters
    public Integer getCodeCmd() {
        return codeCmd;
    }
    
    public void setCodeCmd(Integer codeCmd) {
        this.codeCmd = codeCmd;
    }
    
    public String getClient() {
        return client;
    }
    
    public void setClient(String client) {
        this.client = client;
    }
    
    public Integer getCodePdt() {
        return codePdt;
    }
    
    public void setCodePdt(Integer codePdt) {
        this.codePdt = codePdt;
    }
    
    public Integer getQteCmd() {
        return qteCmd;
    }
    
    public void setQteCmd(Integer qteCmd) {
        this.qteCmd = qteCmd;
    }
    
    public LocalDate getDateCmd() {
        return dateCmd;
    }
    
    public void setDateCmd(LocalDate dateCmd) {
        this.dateCmd = dateCmd;
    }
}