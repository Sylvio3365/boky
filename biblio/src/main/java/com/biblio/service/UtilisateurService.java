package com.biblio.service;

import com.biblio.model.Utilisateur;
import com.biblio.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Optional<Utilisateur> login(String username, String password) {
        return utilisateurRepository.findByUsernameAndPassword(username, password);
    }

    public Utilisateur findById(int iduser) {
        return utilisateurRepository.findById(iduser).orElse(null);
    }
}
