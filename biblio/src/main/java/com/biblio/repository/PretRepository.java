package com.biblio.repository;

import com.biblio.model.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PretRepository extends JpaRepository<Pret, Integer> {

    // Trouver tous les prêts d’un exemplaire
    List<Pret> findByExemplaire_Idexemplaire(Integer idexemplaire);

    // Trouver tous les prêts d’un adhérent
    List<Pret> findByAdherent_Idadherent(Integer idadherent);

    // Trouver les prêts en cours (pas encore rendus)
    List<Pret> findByIdpretNotIn(List<Integer> rendusIdpret);

    @Query("""
                SELECT COUNT(p)
                FROM Pret p
                WHERE p.adherent.idadherent = :idadherent
                  AND p.typepret.idtypepre = 2
                  AND p.idpret NOT IN (
                      SELECT r.pret.idpret FROM Rendu r
                  )
            """)
    long countPretsDomicileNonRendus(@Param("idadherent") Integer idadherent);

    @Query("SELECT p FROM Pret p WHERE p.idpret NOT IN (SELECT r.pret.idpret FROM Rendu r)")
    List<Pret> findPretsNonRendus();
}
