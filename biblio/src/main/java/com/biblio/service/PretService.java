package com.biblio.service;

import com.biblio.model.*;
import com.biblio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.sql.Date;
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
    private AbonnementRepository abonnementRepository;

    @Autowired
    private SanctionRepository sanctionRepository;

    @Autowired
    private RegleRepository regleRepository;

    @Autowired
    private ActiviteRepository activiteRepository;

    public String preterUnLivre(Pret pret) {
        // 1. Chargement des entités depuis la BDD
        Adherent adherent = adherentRepository.findById(pret.getAdherent().getIdadherent()).orElse(null);
        Exemplaire exemplaire = exemplaireRepository.findById(pret.getExemplaire().getIdexemplaire()).orElse(null);
        TypePret typePret = typePretRepository.findById(pret.getTypepret().getIdtypepre()).orElse(null);

        if (adherent == null)
            return "❌ Adhérent introuvable.";
        if (exemplaire == null)
            return "❌ Exemplaire introuvable.";
        if (typePret == null)
            return "❌ Type de prêt introuvable.";

        pret.setAdherent(adherent);
        pret.setExemplaire(exemplaire);
        pret.setTypepret(typePret);

        Livre livre = exemplaire.getLivre();
        if (livre == null)
            return "❌ Livre introuvable pour cet exemplaire.";

        int moisActuel = LocalDate.now().getMonthValue();
        int anneeActuelle = LocalDate.now().getYear();

        boolean estAbonne = abonnementRepository.isAbonneCeMois(adherent.getIdadherent(), moisActuel, anneeActuelle);
        if (!estAbonne) {
            return "❌ Adhérent non abonné pour le mois en cours.";
        }

        Date today = Date.valueOf(LocalDate.now());
        boolean estActif = activiteRepository.existsByAdherent_IdadherentAndDebutBeforeAndFinAfter(
                adherent.getIdadherent(), today, today);

        if (!estActif) {
            return "❌ Adhérent inactif (aucune activité en cours).";
        }

        LocalDate todayLocal = LocalDate.now();
        Date todaySql = Date.valueOf(todayLocal);
        boolean estSanctionne = sanctionRepository
                .existsByAdherent_IdadherentAndDebutLessThanEqualAndFinGreaterThanEqual(
                        adherent.getIdadherent(), today, today);
        if (estSanctionne) {
            return "❌ Adhérent actuellement sanctionné.";
        }
        // 4. Disponibilité de l'exemplaire
        if (!exemplaireRepository.isExemplaireDisponible(exemplaire.getIdexemplaire())) {
            return "❌ Exemplaire déjà prêté et non rendu.";
        }

        // 5. Vérification de l'âge minimum
        RegleLivre regleLivre = livre.getRegleLivre();
        if (regleLivre != null && regleLivre.getAgeMin() != null && adherent.getDtn() != null) {
            int age = adherent.getAge();
            if (age < regleLivre.getAgeMin()) {
                return "❌ L’adhérent a " + age + " ans, mais le livre est réservé à partir de " + regleLivre.getAgeMin()
                        + " ans.";
            }
        }

        // 6. Limite de prêt selon le profil (via regleRepository)
        Regle regle = regleRepository.findByProfil_Idprofil(adherent.getProfil().getIdprofil());
        long nbPretsEnCours = pretRepository.countPretsDomicileNonRendus(adherent.getIdadherent());
        if (nbPretsEnCours >= regle.getNbReservationMax()) {
            return "❌ Nombre de prêts en cours maximum atteint pour ce profil.";
        }
        LocalDate finLocal = todayLocal.plusDays(regle.getNbJourPretMax());
        Date finSql = Date.valueOf(finLocal);

        pret.setDebut(todaySql);
        pret.setFin(finSql);
        pretRepository.save(pret);
        return "✅ Prêt enregistré avec succès.";
    }

    public List<Pret> findPretsNonRendus() {
        return pretRepository.findPretsNonRendus();
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
