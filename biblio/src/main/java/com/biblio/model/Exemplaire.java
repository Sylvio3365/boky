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
@Table(name = "exemplaire")
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idexemplaire;

    @Column(nullable = false)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "idtypepre", nullable = false)
    private TypePret typepret;

    @ManyToOne
    @JoinColumn(name = "idlivre", nullable = false)
    private Livre livre;

    public Integer getIdexemplaire() {
        return idexemplaire;
    }

    public void setIdexemplaire(Integer idexemplaire) {
        this.idexemplaire = idexemplaire;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TypePret getTypepret() {
        return typepret;
    }

    public void setTypepret(TypePret typepret) {
        this.typepret = typepret;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }
}
