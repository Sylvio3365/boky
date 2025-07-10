package com.biblio.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblio.model.Adherent;
import com.biblio.model.Exemplaire;
import com.biblio.model.Livre;
import com.biblio.model.Pret;
import com.biblio.model.Regle;
import com.biblio.model.Reservation;
import com.biblio.model.Utilisateur;
import com.biblio.repository.AbonnementRepository;
import com.biblio.repository.ActiviteRepository;
import com.biblio.repository.ReservationRepository;
import com.biblio.repository.SanctionRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private AdherentService adherentService;
    @Autowired
    private ExemplaireService exemplaireService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private AbonnementRepository abonnementRepository;
    @Autowired
    private SanctionRepository sanctionRepository;
    @Autowired
    private ActiviteRepository activiteRepository;

    public Reservation findById(int id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public String transformerEnPret(int idreservation) {
        Reservation r = reservationRepository.findById(idreservation).orElse(null);
        if (r == null) {
            return "Reservation inexistant.";
        }
        Pret p = new Pret();
        p.setAdherent(r.getAdherent());
        p.setDebut(r.getDatepret());
        p.setFin(null);
        return "/";
    }

    public String reserverByUser(int iduser, int idexemplaire, Date datepret) {
        Utilisateur u = utilisateurService.findById(iduser);
        if (u == null) {
            return "Utilisateur inexistant.";
        }
        if (u.getAdherent() == null) {
            return "Aucun adherent associer a cet utilsateur.";
        }
        return this.reserver(iduser, idexemplaire, datepret);
    }

    public String reserver(int idadherent, int idexemplaire, Date datepret) {
        // 1. Chargement des entités
        Adherent a = adherentService.findById(idadherent);
        if (a == null)
            return "❌ Adhérent inexistant.";

        Exemplaire e = exemplaireService.findById(idexemplaire);
        if (e == null)
            return "❌ Exemplaire inexistant.";

        // 2. Vérification de la disponibilité
        if (!exemplaireService.estDisponible(e.getIdexemplaire())) {
            return "❌ Exemplaire non disponible.";
        }

        // 3. Vérification abonnement courant
        LocalDate now = LocalDate.now();
        int mois = now.getMonthValue();
        int annee = now.getYear();

        boolean estAbonne = abonnementRepository.isAbonneCeMois(a.getIdadherent(), mois, annee);
        if (!estAbonne) {
            return "❌ Adhérent non abonné pour le mois en cours.";
        }

        // 4. Vérification activité en cours
        Date today = Date.valueOf(now);
        boolean actif = activiteRepository.existsByAdherent_IdadherentAndDebutBeforeAndFinAfter(
                a.getIdadherent(), today, today);
        if (!actif) {
            return "❌ Adhérent inactif (aucune activité en cours).";
        }

        // 5. Vérification sanction
        boolean sanction = sanctionRepository
                .existsByAdherent_IdadherentAndDebutLessThanEqualAndFinGreaterThanEqual(
                        a.getIdadherent(), today, today);
        if (sanction) {
            return "❌ Adhérent actuellement sanctionné.";
        }

        // 6. Vérification âge minimum requis
        Livre livre = e.getLivre();
        if (livre != null && livre.getRegleLivre() != null && livre.getRegleLivre().getAgeMin() != null) {
            Integer ageMin = livre.getRegleLivre().getAgeMin();
            if (a.getAge() < ageMin) {
                return "❌ L’adhérent a " + a.getAge() + " ans, mais le livre est réservé à partir de " + ageMin
                        + " ans.";
            }
        }

        // // 7. Vérification du quota de réservation
        // Regle regle =
        // regleRepository.findByProfil_Idprofil(a.getProfil().getIdprofil());
        // long nbResa =
        // reservationRepository.countByAdherent_Idadherent(a.getIdadherent());
        // if (regle != null && nbResa >= regle.getNbReservationMax()) {
        // return "❌ L’adhérent a atteint le nombre maximum de réservations (" +
        // regle.getNbReservationMax() + ").";
        // }

        // 8. Enregistrement
        Reservation r = new Reservation();
        r.setAdherent(a);
        r.setDatepret(datepret);
        r.setExemplaire(e);
        r.setDatereservation(Date.valueOf(now));
        this.save(r);

        return "✅ Exemplaire réservé avec succès par l'adhérent.";
    }

    public Reservation save(Reservation r) {
        return reservationRepository.save(r);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public List<Reservation> findByDatePret(Date d) {
        return reservationRepository.findByDatepret(d);
    }
}
