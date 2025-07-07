package com.biblio.controller;

import com.biblio.model.Pret;
import com.biblio.service.PretService;
import com.biblio.service.RenduService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rendu")
public class RenduController {

    @Autowired
    private PretService pretService;

    @Autowired
    private RenduService renduService;

    // @GetMapping("/non-rendus")
    // public String afficherPretsNonRendus(Model model) {
    //     List<Pret> pretsNonRendus = pretService.findPretsNonRendus();
    //     model.addAttribute("prets", pretsNonRendus);
    //     return "bibliothecaire/rendre";
    // }

    @PostMapping("/rendre/{idpret}")
    public String rendrePret(@PathVariable Integer idpret, Model model) {
        String message = renduService.rendrePretById(idpret);
        model.addAttribute("message", message);
        model.addAttribute("prets", pretService.findPretsNonRendus());
        return "bibliothecaire/rendre";
    }
}
