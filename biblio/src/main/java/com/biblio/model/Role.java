package com.biblio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrole;

    @Column(nullable = false)
    private String nom;

    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}