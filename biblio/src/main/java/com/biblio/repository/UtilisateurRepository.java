package com.biblio.repository;

import com.biblio.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByUsernameAndPassword(String username, String password);

    Optional<Utilisateur> findByUsername(String username);
}
