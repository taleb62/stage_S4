package org.sid.web;

import lombok.Data;
import net.sf.jasperreports.engine.JRException;
import org.sid.entites.DTO.DossierDTO;
import org.sid.entites.DTO.DossierFileDTO;
import org.sid.entites.DTO.LettreDTO;
import org.sid.entites.Dossier;
import org.sid.entites.Lettre;
import org.sid.entites.plan_anuell_achat;
import org.sid.message.ResponseMessage;
import org.sid.services.DossierService;
import org.sid.services.PaaService;
import org.sid.services.ServiceReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/rest/Dossier")
public class DossierController {

    @Autowired
    DossierService dossierService;

    @Autowired
    ServiceReport report;

    @CrossOrigin(origins = "*")
    @GetMapping("/getAllDossier")
    public List<DossierFileDTO> getAllDossier() {
        return dossierService.getAllDossier();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getLettres/{id}")
    public List<Lettre> getLettres(@PathVariable Integer id) {
        return dossierService.getAllLettres(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/LettresNotInPlis/{id}")
    public List<Lettre> LettresNotInPlis(@PathVariable Integer id) {
        return dossierService.LettresNotInPlis(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/create")
    public Dossier createDosssier(@RequestBody DossierDTO data) throws IOException {
        System.out.println("**********************************" + data);
        return dossierService.createDosssier(data);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/createLettre")
    public List<Lettre> createLettre(@RequestBody LettreDTO data) throws IOException {
        System.out.println("**********************************" + data);
        return dossierService.createLettre(data);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/report/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity generateReport(@PathVariable Integer id, @RequestParam("report-name") String reportName)
            throws IOException, JRException {
        return report.exportReportLettre(id, reportName);
    }

    @PutMapping("/{id}")
    public Dossier updateEtat(@PathVariable Integer id) {
        // TODO: process POST request

        

        return dossierService.updateEtat(id);

    }

}
