package com.biblio.repository;

import java.sql.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblio.model.Activite;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Integer> {
    boolean existsByAdherent_IdadherentAndDebutBeforeAndFinAfter(
            Integer idadherent, Date today1, Date today2);
}
