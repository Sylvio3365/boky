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
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idutilisateur;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "idadherent")
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "idrole", nullable = false)
    private Role role;

    public Integer getIdutilisateur() { return idutilisateur; }
    public void setIdutilisateur(Integer idutilisateur) { this.idutilisateur = idutilisateur; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Adherent getAdherent() { return adherent; }
    public void setAdherent(Adherent adherent) { this.adherent = adherent; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}