package com.biblio.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pret")
public class Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpret;

    private Date debut;
    private Date fin;

    @ManyToOne
    @JoinColumn(name = "idadherent", nullable = false)
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "idtypepre", nullable = false)
    private TypePret typepret;

    @ManyToOne
    @JoinColumn(name = "idexemplaire", nullable = false)
    private Exemplaire exemplaire;

    public Integer getIdpret() {
        return idpret;
    }

    public void setIdpret(Integer idpret) {
        this.idpret = idpret;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public TypePret getTypepret() {
        return typepret;
    }

    public void setTypepret(TypePret typepret) {
        this.typepret = typepret;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }
}