package com.biblio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.biblio.model.Abonnement;

public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
    @Query("SELECT COUNT(a) > 0 FROM Abonnement a WHERE a.adherent.idadherent = :idadherent AND a.mois = :mois AND a.annee = :annee")
    boolean isAbonneCeMois(@Param("idadherent") Integer idadherent, @Param("mois") Integer mois,
            @Param("annee") Integer annee);

}
