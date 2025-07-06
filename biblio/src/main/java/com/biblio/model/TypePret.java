package com.biblio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "typepret")
public class TypePret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtypepre;

    @Column(nullable = false)
    private String nom;

    // Getters and Setters
    public Integer getIdtypepre() {
        return idtypepre;
    }

    public void setIdtypepre(Integer idtypepre) {
        this.idtypepre = idtypepre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
