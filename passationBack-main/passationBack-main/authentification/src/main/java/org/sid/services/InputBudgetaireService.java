package org.sid.services;

import org.sid.dao.EtablissementRepository;
import org.sid.dao.InputBudgetaireRepository;
import org.sid.dao.modeRepository;
import org.sid.dao.typeRepository;
import org.sid.entites.Etablissement;
import org.sid.entites.InputBudgetaire;
import org.sid.entites.mod_passation;
import org.sid.entites.type_marche;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InputBudgetaireService {

        @Autowired
        private InputBudgetaireRepository inputBudgetaireRepository;

        @Autowired
        private EtablissementRepository etablissementRepository;

        @Autowired
        private typeRepository typeMarcher;

        @Autowired
        private modeRepository modeRepository;

        public InputBudgetaire createInputBudgetaire(Integer typeSelectionid, Integer typeMarcherid,
                        Integer etablissementId) {
                int currentYear = LocalDate.now().getYear();

                mod_passation typeSelection = modeRepository.findById(typeSelectionid)
                                .orElseThrow(() -> new IllegalArgumentException("Type de selection not found"));
                                
                type_marche typeMarche = typeMarcher.findById(typeMarcherid).orElseThrow(() -> new IllegalArgumentException("Type de marche not found"));

                InputBudgetaire lastInput = inputBudgetaireRepository
                                .findTopByYearAndTypeSelectionOrderByUniqueIdDesc(currentYear, typeSelection);
                int newUniqueId = (lastInput != null) ? lastInput.getUniqueId() + 1 : 1;

                Etablissement etablissement = etablissementRepository.findById(etablissementId)
                                .orElseThrow(() -> new IllegalArgumentException("Etablissement not found"));

                String budgetNumber = String.format("%d-%d-%01d-%04d", currentYear, etablissement.getId(),
                                typeSelection.getId(), newUniqueId);

                InputBudgetaire inputBudgetaire = new InputBudgetaire(budgetNumber, currentYear,
                                newUniqueId, LocalDate.now(), etablissement, typeSelection, typeMarche);
                return inputBudgetaireRepository.save(inputBudgetaire);
        }
}
