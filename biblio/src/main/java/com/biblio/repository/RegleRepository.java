package com.biblio.repository;

import com.biblio.model.Regle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegleRepository extends JpaRepository<Regle, Integer> {

    // Récupère la règle associée à un profil donné
    Regle findByProfil_Idprofil(Integer idprofil);
}
