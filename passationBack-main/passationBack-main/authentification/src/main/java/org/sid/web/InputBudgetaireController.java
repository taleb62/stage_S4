package org.sid.web;
import org.springframework.http.MediaType;

import org.sid.entites.InputBudgetaire;
import org.sid.services.InputBudgetaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/rest/input")
public class InputBudgetaireController {

    @Autowired
    private InputBudgetaireService inputBudgetaireService;

    @PostMapping()
    public InputBudgetaire createInputBudgetaire(@RequestParam Integer typeSelectionid,@RequestParam Integer typeMarcherid,
            @RequestParam Integer etablissementId) {
        return inputBudgetaireService.createInputBudgetaire(typeSelectionid,typeMarcherid, etablissementId);
    }
}
