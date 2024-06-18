package org.sid.web;

import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;
import net.sf.jasperreports.engine.JRException;
import org.sid.entites.plan_anuell_achat;
import org.sid.services.PaaService;
import org.sid.services.PaaServiceImpl;
import org.sid.services.ServiceReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/rest/Paa")
public class PaaController {

    @Autowired
    PaaService paaService;

    @Autowired
    PaaServiceImpl service;

    @Autowired
    ServiceReport report;

    @CrossOrigin(origins = "*")
    @GetMapping("/getAllPaaa")
    public List<plan_anuell_achat> getAllPaaa() {
        return paaService.getAllPaa();
    }

    @GetMapping("/listPaa")
    public ArrayNode getListDocs() {
        return service.getListPaa();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updatePaa")
    public plan_anuell_achat updatePaa(@RequestBody PaaFormProcedure data) {
        System.out.println("**********************************" + data);
        return paaService.updatePaa(data.getId(), data.getOrigine(), data.getDestinataire());
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/report/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity generateReport(@PathVariable Integer id) throws IOException, JRException {
        return report.exportReport(id);
    }

    @PutMapping("valider/{id}")
    public plan_anuell_achat validerPaa(@PathVariable Integer id) {

        return service.validatePlanAnuellAchat(id);
    }
}

@Data
class PaaFormProcedure {
    private Integer id;
    private String origine;
    private String destinataire;
}
