package org.sid.web;

import org.springframework.http.MediaType;

import java.util.List;

import org.sid.dao.modeRepository;
import org.sid.dao.typeRepository;
import org.sid.entites.InputBudgetaire;
import org.sid.entites.mod_passation;
import org.sid.entites.type_marche;
import org.sid.services.InputBudgetaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/api/rest/input")
public class InputBudgetaireController {

    @Autowired
    private InputBudgetaireService inputBudgetaireService;

    @Autowired
    private typeRepository contrat;
    @Autowired
    private modeRepository mode;
    
    @PostMapping()
    public InputBudgetaire createInputBudgetaire(@RequestParam Integer typeSelectionid,
            @RequestParam Integer typeMarcherid,
            @RequestParam Integer etablissementId) {
        return inputBudgetaireService.createInputBudgetaire(typeSelectionid, typeMarcherid, etablissementId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InputBudgetaire> getInputs() {
        return inputBudgetaireService.getInputs();
    }


    @GetMapping("/contrat")
    public List<type_marche> getContrats() {
        return contrat.findAll();
    }
    @GetMapping("/mode")
    public List<mod_passation> getMode() {
        return mode.findAll();
    }


    @DeleteMapping("/{id}")
    public void deleteInput(@PathVariable Long id) {
        inputBudgetaireService.deleteInput(id);
        
    }

    

}
