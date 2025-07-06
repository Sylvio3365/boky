package com.biblio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BibliothecaireController {

    @GetMapping("/bibliothecaire")
    public String afficherMenuBibliothecaire(Model model) {
        return "bibliothecaire/index";
    }
}
