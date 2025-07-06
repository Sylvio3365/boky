package com.biblio.service;

import com.biblio.model.*;
import com.biblio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PretService {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private TypePretRepository typePretRepository;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private TypePretService typePretService;

    public String preterUnLivre(Pret pret) {
        Adherent adherent = adherentService.getAdherentById(pret.getAdherent().getIdadherent()).orElse(null);
        Exemplaire exemplaire = exemplaireService.getExemplaireById(pret.getExemplaire().getIdexemplaire()).orElse(null);
        TypePret typePret = typePretService.findById(pret.getTypepret().getIdtypepre());

        pret.setAdherent(adherent);
        pret.setExemplaire(exemplaire);
        pret.setTypepret(typePret);

        Livre livre = exemplaire.getLivre();
        if (livre == null) {
            return "❌ Livre introuvable pour cet exemplaire.";
        }

        if (adherent == null) {
            return "❌ Adhérent introuvable.";
        }

        if (!exemplaireRepository.isExemplaireDisponible(exemplaire.getIdexemplaire())) {
            return "❌ Exemplaire non disponible (déjà prêté et non rendu).";
        }

        RegleLivre regleLivre = livre.getRegleLivre();
        if (regleLivre != null && regleLivre.getAgeMin() != null && adherent.getDtn() != null) {
            int age = calculerAge(adherent.getDtn());
            if (age < regleLivre.getAgeMin()) {
                return "❌ L’adhérent est trop jeune pour emprunter ce livre.";
            }
        }
        
        // ✅ Enregistrement du prêt
        pretRepository.save(pret);
        return "✅ Prêt enregistré avec succès.";
    }

    private int calculerAge(Date dtn) {
        Date now = new Date();
        long ageMillis = now.getTime() - dtn.getTime();
        return (int) (ageMillis / 1000 / 60 / 60 / 24 / 365);
    }

    public List<Pret> getAllPrets() {
        return pretRepository.findAll();
    }

    public Pret savePret(Pret pret) {
        return pretRepository.save(pret);
    }

    public Pret getPretById(Integer id) {
        return pretRepository.findById(id).orElse(null);
    }

    public void deletePret(Integer id) {
        pretRepository.deleteById(id);
    }

    public List<Pret> getPretsParAdherent(Integer idadherent) {
        return pretRepository.findByAdherent_Idadherent(idadherent);
    }

    public List<Pret> getPretsParExemplaire(Integer idexemplaire) {
        return pretRepository.findByExemplaire_Idexemplaire(idexemplaire);
    }
}
