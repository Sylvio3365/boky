package com.biblio.controller;

import com.biblio.model.Adherent;
import com.biblio.model.Exemplaire;
import com.biblio.model.Pret;
import com.biblio.model.TypePret;
import com.biblio.service.AdherentService;
import com.biblio.service.ExemplaireService;
import com.biblio.service.PretService;
import com.biblio.service.TypePretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pret")
public class PretController {

    @Autowired
    private PretService pretService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private TypePretService typePretService;

    @GetMapping("/form")
    public String afficherFormulairePret(Model model) {
        List<Exemplaire> exemplaires = exemplaireService.getAllExemplaires();
        List<Adherent> adherents = adherentService.getAllAdherents();
        List<TypePret> typeprets = typePretService.findAll();

        model.addAttribute("exemplaires", exemplaires);
        model.addAttribute("adherents", adherents);
        model.addAttribute("typeprets", typeprets);
        model.addAttribute("pret", new Pret());
        model.addAttribute("message", "");

        return "bibliothecaire/pret"; // src/main/resources/templates/bibliothecaire/pret.html
    }

    @PostMapping("/ajouter")
    public String ajouterPret(@ModelAttribute Pret pret, Model model) {

        // Appel au service pour validation métier et enregistrement
        String message = pretService.preterUnLivre(pret);

        // Rafraîchir les listes pour réafficher le formulaire
        List<Exemplaire> exemplaires = exemplaireService.getAllExemplaires();
        List<Adherent> adherents = adherentService.getAllAdherents();
        List<TypePret> typeprets = typePretService.findAll();

        model.addAttribute("exemplaires", exemplaires);
        model.addAttribute("adherents", adherents);
        model.addAttribute("typeprets", typeprets);
        model.addAttribute("pret", new Pret());
        model.addAttribute("message", message);

        return "bibliothecaire/pret";
    }

}
