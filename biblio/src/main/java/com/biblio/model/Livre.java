package com.biblio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "livre")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idlivre;

    @Column(nullable = false)
    private String nom;

    private String auteur;

    @ManyToOne
    @JoinColumn(name = "idreglelivre", nullable = false)
    private RegleLivre regleLivre;

    // Getters and Setters
    public Integer getIdlivre() {
        return idlivre;
    }

    public void setIdlivre(Integer idlivre) {
        this.idlivre = idlivre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public RegleLivre getRegleLivre() {
        return regleLivre;
    }

    public void setRegleLivre(RegleLivre regleLivre) {
        this.regleLivre = regleLivre;
    }
}
