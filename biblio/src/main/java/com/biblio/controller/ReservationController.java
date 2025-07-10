package com.biblio.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblio.model.Adherent;
import com.biblio.model.Exemplaire;
import com.biblio.model.Pret;
import com.biblio.model.Reservation;
import com.biblio.model.Utilisateur;
import com.biblio.service.ExemplaireService;
import com.biblio.service.PretService;
import com.biblio.service.ReservationService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reserver")
public class ReservationController {

    @Autowired
    private ExemplaireService exemplaireService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private PretService pretService;

    @GetMapping("/form")
    private String afficherForm(Model m) {
        List<Exemplaire> exemplaires = exemplaireService.getAllExemplaires();
        m.addAttribute("exemplaires", exemplaires);
        return "adherent/reservation";
    }

    @PostMapping("/save")
    public String reserver(@RequestParam("idexemplaire") int idexemplaire,
            @RequestParam("datepret") Date datepret,
            HttpSession session,
            Model model) {

        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateurConnecte");
        if (utilisateur == null) {
            model.addAttribute("erreur", "Vous devez être connecté comme adhérent.");
            return "login/login";
        }
        Adherent adherent = utilisateur.getAdherent();
        String message = reservationService.reserver(adherent.getIdadherent(), idexemplaire, datepret);
        if (message.toLowerCase().contains("non") || message.toLowerCase().contains("inexistant")) {
            model.addAttribute("erreur", message);
        } else {
            model.addAttribute("message", message);
        }

        // Recharger la liste des exemplaires après action
        List<Exemplaire> exemplaires = exemplaireService.getAllExemplaires();
        model.addAttribute("exemplaires", exemplaires);

        return "adherent/reservation";
    }

    @GetMapping("/avalider")
    public String afficherReservation(@RequestParam(value = "datepret", required = false) Date datepret, Model m) {
        if (datepret == null) {
            datepret = Date.valueOf(LocalDate.now());
        }
        m.addAttribute("date", datepret);
        List<Reservation> r = reservationService.findByDatePret(datepret);
        m.addAttribute("reservations", r);
        return "bibliothecaire/reservation";
    }

    @PostMapping("/valider")
    public String validerReservation(@RequestParam("id") int idReservation) {
        Pret p = new Pret();
        Reservation r = reservationService.findById(idReservation);
        if (re) {
            
        }
        String message = pretService.preterUnLivre(null)
        return "bibliothecaire/reservation";
    }
}
