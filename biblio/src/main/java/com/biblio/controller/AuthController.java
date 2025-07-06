package com.biblio.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.biblio.model.Utilisateur;
import com.biblio.service.UtilisateurService;

@Controller
public class AuthController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/login")
    public String afficherLoginPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("message", "Bienvenue sur la page de connexion");
        if (error != null) {
            model.addAttribute("erreur", "Identifiants incorrects.");
        }
        return "login/login";   
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Optional<Utilisateur> utilisateur = utilisateurService.login(username, password);
        if (utilisateur.isPresent()) {
            String role = utilisateur.get().getRole().getNom().toLowerCase();
            if (role.equals("bibliothecaire")) {
                return "redirect:/bibliothecaire";
            } else if (role.equals("admin")) {
                return "redirect:/admin";
            } else if (role.equals("adherent")) {
                return "redirect:/adherent";
            }
        }
        return "redirect:/login?error=true";
    }
}
