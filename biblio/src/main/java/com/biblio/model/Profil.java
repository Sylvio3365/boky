package com.biblio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "profil")
public class Profil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idprofil;

    @Column(nullable = false)
    private String nom;

    public Integer getIdprofil() {
        return idprofil;
    }

    public void setIdprofil(Integer idprofil) {
        this.idprofil = idprofil;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}