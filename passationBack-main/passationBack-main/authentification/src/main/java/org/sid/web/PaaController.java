package org.sid.web;

import java.io.IOException;
import java.util.List;

import org.sid.entites.plan_anuell_achat;
import org.sid.entites.DTO.PaaFormProcedure;
import org.sid.services.PaaService;
import org.sid.services.PaaServiceImpl;
import org.sid.services.ServiceReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ArrayNode;

import net.sf.jasperreports.engine.JRException;


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
    @CrossOrigin(origins = "*")
    @PostMapping("/addPaa")
    public ResponseEntity<plan_anuell_achat> addPaa(@RequestBody PaaFormProcedure data) {
        plan_anuell_achat newPaa = paaService.addPaa(data);
        return ResponseEntity.ok(newPaa);
    }




    @PutMapping("valider/{id}")
    public plan_anuell_achat validerPaa(@PathVariable Integer id) {

        return service.validatePlanAnuellAchat(id);
    }

 @CrossOrigin(origins = "*")
@DeleteMapping("/deletePaa/{id}")
public ResponseEntity<String> deletePaa(@PathVariable Integer id) {
    paaService.deletePaa(id);
    return ResponseEntity.ok("PAA deleted successfully");
}

}


