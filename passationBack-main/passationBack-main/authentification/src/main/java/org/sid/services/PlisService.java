package org.sid.services;

import org.sid.dao.FileDBRepository;
import org.sid.dao.PlisRepository;
import org.sid.entites.DTO.DossierDTO;
import org.sid.entites.DTO.PlisDTO;
import org.sid.entites.Dossier;
import org.sid.entites.Lettre;
import org.sid.entites.Plis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PlisService {

    @Autowired
    private PlisRepository plisRepository;

    public Plis createPlis(PlisDTO data) throws IOException {
        Plis plis = new Plis(data.getDateReception(), data.getPosteOuCourrierExpress(),
                data.getPorteur(),data.getObservation(),data.getIdDossier(),data.getIdLettre());
        Plis  plis1 =plisRepository.save(plis);
//        updatePaaDossier(data.getIdPaa());
        return plis1;
    }



    public List<Plis> getPlisByIdDossier(Integer id) {
        return plisRepository.getPlis(id);
    }
}
