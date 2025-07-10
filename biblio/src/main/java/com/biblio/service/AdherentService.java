package com.biblio.service;

import com.biblio.model.Adherent;
import com.biblio.repository.AdherentRepository;
import com.biblio.repository.SanctionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AdherentService {

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private SanctionRepository sanctionRepository;

    public Adherent findById(int id) {
        return adherentRepository.findById(id).orElse(null);
    }

    public boolean estAdherentSanctionne(Adherent adherent) {
        Date today = Date.valueOf(LocalDate.now());
        return sanctionRepository.existsByAdherent_IdadherentAndDebutBeforeAndFinAfter(
                adherent.getIdadherent(), today, today);
    }

    public List<Adherent> getAllAdherents() {
        return adherentRepository.findAll();
    }

    public Optional<Adherent> getAdherentById(Integer id) {
        return adherentRepository.findById(id);
    }

    public Adherent saveAdherent(Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    public void deleteAdherent(Integer id) {
        adherentRepository.deleteById(id);
    }
}
