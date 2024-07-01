package org.sid.services;

import org.sid.dao.DossierRepository;
import org.sid.dao.LettreRepository;
import org.sid.dao.planRepository;
import org.sid.entites.DTO.DossierDTO;
import org.sid.entites.DTO.DossierFileDTO;
import org.sid.entites.DTO.LettreDTO;
import org.sid.entites.Dossier;
import org.sid.entites.Lettre;
import org.sid.entites.plan_anuell_achat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DossierService {
    @Autowired
    private DossierRepository dossierRepository;

    @Autowired
    private LettreRepository lettreRepository;

    @Autowired
    private planRepository planRepository;

    public List<Dossier> getAllDossier() {
        return dossierRepository.findAll();
    }

    // public List<Lettre> getAllLettres(Integer id) {
    //     return dossierRepository.getLettres(id);
    // }

    // public List<Lettre> LettresNotInPlis(Integer id) {
    //     return dossierRepository.LettresNotInPlis(id);
    // }

    public Dossier createDosssier(Dossier data) throws IOException {
        Dossier dossier = dossierRepository.save(data);
        return dossier;
    }

    // public List<Lettre> createLettre(LettreDTO data) throws IOException {
    //     List<Lettre> lisLettres = new ArrayList<>();
    //     data.getNomFournissuer().forEach(foun -> {
    //         Lettre lettre = new Lettre(foun.toString(), data.getNonAutoriteContractante(),
    //                 data.getDateLimiteDepot(), data.getDateOverturePlis(), data.getDateAnterieurDepot(),
    //                 data.getLieuOverturePlis(), data.getIdDossier(), data.getFkIduser());
    //         lisLettres.add(lettre);
    //     });
    //     List<Lettre> Listlettres = lettreRepository.saveAll(lisLettres);
    //     updateDossier(data.getIdDossier());
    //     return Listlettres;
    // }

    public plan_anuell_achat updatePaaDossier(Integer id) {
        plan_anuell_achat paa = planRepository.getPaa(id);
        if (paa == null)
            throw new RuntimeException("le PAA non trouvé !");
        planRepository.updatePaaDossier(!paa.isDosssierCree(), id);
        plan_anuell_achat paa1 = planRepository.getPaa(id);
        return paa1;
    }

    // public Dossier updateDossier(Integer id) {
    //     Dossier dossier = dossierRepository.getDossier(id);
    //     if (dossier == null)
    //         throw new RuntimeException("le Dossier non trouvé !");
    //     dossierRepository.updateDossier(!dossier.isLettreCree(), id);
    //     Dossier dossier1 = dossierRepository.getDossier(id);
    //     return dossier1;
    // }

    // public Dossier updateEtat(Integer id) {
    //     Dossier dossier = dossierRepository.getDossier(id);
    //     if (dossier == null)
    //         throw new RuntimeException("le Dossier non trouvé !");
    //     dossierRepository.updateEtat(!dossier.isOuvert(),id);

    //     Dossier dossier1 = dossierRepository.getDossier(id);
    //     return dossier1;
    // }
}
