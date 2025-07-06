package com.biblio.repository;

import com.biblio.model.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SanctionRepository extends JpaRepository<Sanction, Integer> {

    boolean existsByAdherent_IdadherentAndDebutBeforeAndFinAfter(Integer idadherent, Date debut, Date fin);

    boolean existsByAdherent_IdadherentAndDebutLessThanEqualAndFinGreaterThanEqual(
            Integer idadherent, Date today1, Date today2);

}
