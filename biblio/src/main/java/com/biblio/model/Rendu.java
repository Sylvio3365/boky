package com.biblio.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rendu")
public class Rendu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrendu;

    @Column(name = "date_", nullable = false)
    private Date date;

    @OneToOne
    @JoinColumn(name = "idpret", unique = true, nullable = false)
    private Pret pret;

    public Integer getIdrendu() {
        return idrendu;
    }

    public void setIdrendu(Integer idrendu) {
        this.idrendu = idrendu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }
}