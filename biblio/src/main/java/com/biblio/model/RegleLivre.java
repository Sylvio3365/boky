package com.biblio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reglelivre")
public class RegleLivre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idreglelivre;

    private Integer ageMin;

    // Getters and Setters
    public Integer getIdreglelivre() {
        return idreglelivre;
    }

    public void setIdreglelivre(Integer idreglelivre) {
        this.idreglelivre = idreglelivre;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }
}