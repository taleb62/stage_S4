package org.sid.web;

import org.sid.entites.DTO.LettreDTO;
import org.sid.entites.DTO.PlisDTO;
import org.sid.entites.Lettre;
import org.sid.entites.Plis;
import org.sid.services.DossierService;
import org.sid.services.PlisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/rest/Plis")
public class PlisController {

    @Autowired
    PlisService plisService;

    @CrossOrigin(origins = "*")
    @PostMapping("/createPlis")
    public Plis createPlis(@RequestBody PlisDTO data) throws IOException {
        System.out.println("**********************************" + data);
        return plisService.createPlis(data);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getPlisByIdDossier/{id}")
    public List<Plis> getPlisByIdDossier(@PathVariable Integer id){
        return plisService.getPlisByIdDossier(id);
    }
}
