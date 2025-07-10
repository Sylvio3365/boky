package com.biblio.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession; // ou javax.servlet.http.HttpSession selon ta version
import com.biblio.model.Utilisateur;
import com.biblio.service.UtilisateurService;

@Controller
public class AuthController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Supprimer toutes les données de session
        return "redirect:/login?logout=true"; // Redirection avec message
    }

    @GetMapping("/login")
    public String afficherLoginPage(@RequestParam(required = false) String error,
            @RequestParam(required = false) String logout,
            Model model) {
        model.addAttribute("message", "Bienvenue sur la page de connexion");

        if (error != null) {
            model.addAttribute("erreur", "Identifiants incorrects.");
        }

        if (logout != null) {
            model.addAttribute("info", "Vous avez été déconnecté avec succès.");
        }

        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Optional<Utilisateur> utilisateur = utilisateurService.login(username, password);

        if (utilisateur.isPresent()) {
            session.setAttribute("utilisateurConnecte", utilisateur.get());
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
