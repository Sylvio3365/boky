package com.biblio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "regle")
public class Regle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idregle;

    @Column(nullable = false)
    private int nbJourPretMax;

    @Column(nullable = false)
    private int nbReservationMax;

    @Column(nullable = false)
    private int nbProlongementMax;

    @ManyToOne
    @JoinColumn(name = "idprofil", nullable = false)
    private Profil profil;

    @Column(nullable = false)
    private Integer nbjoursanction;

    public Integer getNbjoursanction() {
        return nbjoursanction;
    }

    public void setNbjoursanction(Integer nbjoursanction) {
        this.nbjoursanction = nbjoursanction;
    }

    // Getters and Setters
    public Integer getIdregle() {
        return idregle;
    }

    public void setIdregle(Integer idregle) {
        this.idregle = idregle;
    }

    public int getNbJourPretMax() {
        return nbJourPretMax;
    }

    public void setNbJourPretMax(int val) {
        this.nbJourPretMax = val;
    }

    public int getNbReservationMax() {
        return nbReservationMax;
    }

    public void setNbReservationMax(int val) {
        this.nbReservationMax = val;
    }

    public int getNbProlongementMax() {
        return nbProlongementMax;
    }

    public void setNbProlongementMax(int val) {
        this.nbProlongementMax = val;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }
}