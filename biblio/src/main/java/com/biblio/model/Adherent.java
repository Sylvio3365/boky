package com.biblio.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "adherent")
public class Adherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idadherent;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    private Date dtn;

    @ManyToOne
    @JoinColumn(name = "idprofil", nullable = false)
    private Profil profil;

    public int getAge() {
        if (this.dtn == null)
            return 0;
        LocalDate birthDate = this.dtn.toLocalDate();
        LocalDate today = LocalDate.now();
        return Period.between(birthDate, today).getYears();
    }

    // Getters and Setters
    public Integer getIdadherent() {
        return idadherent;
    }

    public void setIdadherent(Integer idadherent) {
        this.idadherent = idadherent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDtn() {
        return dtn;
    }

    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }
}
