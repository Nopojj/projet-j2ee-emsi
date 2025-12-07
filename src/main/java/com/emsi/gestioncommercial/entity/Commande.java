package com.emsi.gestioncommercial.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tous_commandes")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codeTousCmd;

    @Column(name = "codeCmd", nullable = false)
    private Integer codeCmd;

    @Column(name = "client", length = 20, nullable = false)
    private String client;

    @Column(name = "codePdt", nullable = false)
    private Integer codePdt;

    @Column(name = "qteCmd", nullable = false)
    private Integer qteCmd;

    @Column(name = "dateCmd", nullable = false)
    private LocalDate dateCmd;

    // Constructeurs
    public Commande() {}

    public Commande(Integer codeCmd, String client, Integer codePdt, Integer qteCmd, LocalDate dateCmd) {
        this.codeCmd = codeCmd;
        this.client = client;
        this.codePdt = codePdt;
        this.qteCmd = qteCmd;
        this.dateCmd = dateCmd;
    }

    // Getters et Setters
    public Integer getCodeTousCmd() {
        return codeTousCmd;
    }

    public void setCodeTousCmd(Integer codeTousCmd) {
        this.codeTousCmd = codeTousCmd;
    }

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