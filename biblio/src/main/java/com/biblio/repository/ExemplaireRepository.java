package com.biblio.repository;

import com.biblio.model.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
    
    @Query("SELECT CASE WHEN COUNT(p) = COUNT(r) THEN true ELSE false END " +
            "FROM Pret p LEFT JOIN Rendu r ON p = r.pret " +
            "WHERE p.exemplaire.idexemplaire = :idExemplaire")
    boolean isExemplaireDisponible(@Param("idExemplaire") Integer idExemplaire);

}
