package com.emsi.gestion_vente.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codeUser;
    
    @Column(unique = true, length = 20)
    private String login;
    
    @Column(length = 20)
    private String pass;
    
    // Constructeurs
    public Users() {}
    
    public Users(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }
    
    // Getters et Setters
    public Integer getCodeUser() {
        return codeUser;
    }
    
    public void setCodeUser(Integer codeUser) {
        this.codeUser = codeUser;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPass() {
        return pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }
}