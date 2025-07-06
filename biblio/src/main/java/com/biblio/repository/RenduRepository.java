package com.biblio.repository;

import com.biblio.model.Rendu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenduRepository extends JpaRepository<Rendu, Integer> {
    // Exemples de méthodes personnalisées
    boolean existsByPret_Idpret(Integer idpret);
}
