package com.biblio.service;

import com.biblio.model.Adherent;
import com.biblio.model.Pret;
import com.biblio.model.Profil;
import com.biblio.model.Regle;
import com.biblio.model.Rendu;
import com.biblio.model.Sanction;
import com.biblio.repository.AdherentRepository;
import com.biblio.repository.PretRepository;
import com.biblio.repository.RegleRepository;
import com.biblio.repository.RenduRepository;
import com.biblio.repository.SanctionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class RenduService {

    @Autowired
    private PretRepository pretRepository;

    @Autowired
    private RenduRepository renduRepository;

    @Autowired
    private RegleRepository regleRepository;

    @Autowired
    private SanctionRepository sanctionRepository;

    public String rendrePretById(Integer idpret) {
        // 1. Rechercher le prêt
        Pret pret = pretRepository.findById(idpret).orElse(null);
        if (pret == null) {
            return "❌ Prêt introuvable.";
        }

        // 2. Vérifier s'il est déjà rendu
        boolean dejaRendu = renduRepository.existsByPret_Idpret(idpret);
        if (dejaRendu) {
            return "❌ Ce prêt a déjà été rendu.";
        }

        // 3. Enregistrer le rendu
        Rendu rendu = new Rendu();
        rendu.setPret(pret);
        Date today = Date.valueOf(LocalDate.now());
        rendu.setDate(today);

        // 4. Vérification de retard
        if (pret.getFin().before(today)) {
            // Appliquer sanction
            Adherent adherent = pret.getAdherent();
            Profil profil = adherent.getProfil();
            Regle regle = regleRepository.findByProfil_Idprofil(profil.getIdprofil());

            int nbJoursSanction = regle.getNbjoursanction(); // Colonne récemment ajoutée
            LocalDate debutSanction = LocalDate.now();
            LocalDate finSanction = debutSanction.plusDays(nbJoursSanction);

            Sanction sanction = new Sanction();
            sanction.setAdherent(adherent);
            sanction.setDebut(Date.valueOf(debutSanction));
            sanction.setFin(Date.valueOf(finSanction));
            sanctionRepository.save(sanction);
            renduRepository.save(rendu);
            return "✅ Prêt rendu avec succès, mais une sanction de " + nbJoursSanction
                    + " jours a été appliquée pour retard.";
        }

        // 5. Enregistrement normal
        renduRepository.save(rendu);
        return "✅ Prêt rendu dans les délais.";
    }

}
