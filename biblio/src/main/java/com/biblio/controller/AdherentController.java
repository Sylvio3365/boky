package com.biblio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdherentController {

    @GetMapping("/adherent")
    public String afficherPageAdherent() {
        return "adherent/index";
    }

}
