package org.sid.services;

import java.nio.file.Files;
import java.nio.file.Paths;
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
    private ReportService reportService;

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

    

    public ProcedurePaa createProcedure(ProcedurePaa procedurePaa) {
        ProcedurePaa savedProcedure = repository.save(procedurePaa);

        try {
            byte[] report = reportService.generateProcedureReport(savedProcedure);
            String reportPath = "C:/Users/lapto/Desktop/stage_project/uploads/procedures/report_" + savedProcedure.getId() + ".pdf";
            Files.write(Paths.get(reportPath), report);

            // Mettre à jour le champ pathInitialProcedure
            savedProcedure.setPathInitialProcedure(reportPath);
            repository.save(savedProcedure);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return savedProcedure;
    }}
