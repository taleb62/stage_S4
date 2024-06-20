package org.sid.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.sid.dao.ProcedureRepository;
import org.sid.dao.planRepository;
import org.sid.entites.ProcedurePaa;
import org.sid.entites.plan_anuell_achat;
import org.sid.entites.DTO.PaaProcedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProcedurePaaService {

    @Autowired
    private ProcedureRepository repository;

    @Autowired
    private planRepository paaRepository;

    

    public List<ProcedurePaa> findAll() {
        return repository.findAll();
    }

    public Optional<ProcedurePaa> findById(Integer id) {
        return repository.findById(id);
    }

    public ProcedurePaa save(ProcedurePaa procedurePaa) {
        return repository.save(procedurePaa);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public ProcedurePaa createProcedure(
            Integer paaId,
            String origin,
            String destinataire,
            String sourceFinanciere,
            String description,
            LocalDate deadlineEstime,
            double montant) {

        // if (paaId == null || origin == null || destinataire == null || sourceFinanciere == null || description == null
        //         || deadlineEstime == null || montant == 0) {
            
        //             System.out.println("les donnee sont null");
            
        // }

        plan_anuell_achat paa = paaRepository.findById(paaId)
                .orElseThrow(() -> new RuntimeException("PAA not found"));

    

        // if (paa.getMontantRestant() < montant) {
        //     throw new RuntimeException("Insufficient funds in PAA");
        // }


        // if (paaId == null || origin == null) {
        // throw new IllegalArgumentException("idpaa and origin cannot be null");
        // }

        ProcedurePaa procedure = new ProcedurePaa();
        procedure.setOrigin(origin);
        procedure.setMontant(montant);
        procedure.setPaa(paa);
        procedure.setDestinataire(destinataire);
        procedure.setSourceFinanciere(sourceFinanciere);
        procedure.setDescription(description);
        procedure.setDeadlineEstime(deadlineEstime);

        paa.setMontantRestant(paa.getMontantRestant() - montant);
        paa.getProcedures().add(procedure);
        paaRepository.save(paa); // Save the PAA to update the remaining amount
        return repository.save(procedure); // Save the procedure
    }


}
