package org.sid.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
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
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProcedurePaaService {

    @Autowired
    private ProcedureRepository repository;

    @Autowired
    private ReportService reportService;

    @Autowired
    private planRepository plan;

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

    public ProcedurePaa createProcedure(ProcedurePaa procedurePaa, MultipartFile file) {
        plan_anuell_achat paa = plan.findById(procedurePaa.getPaa().getId())
                .orElseThrow(() -> new IllegalArgumentException("Paa not found"));
        
        paa.setMontantRestant(paa.getMntEstimatif()-procedurePaa.getMontant());
        ProcedurePaa savedProcedure = repository.save(procedurePaa);
        try {
            String directoryPath = "C:\\Users\\lapto\\Desktop\\stage_project_old\\uploads\\procedures\\besoins";
            Path directory = Paths.get(directoryPath);

            // Check if the directory exists, if not, create it
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            String fileName = file.getOriginalFilename();
            Path filePath = directory.resolve(savedProcedure.getId() + "_" + fileName);
            Files.copy(file.getInputStream(), filePath);

            byte[] report = reportService.generateProcedureReport(savedProcedure);
            String reportPath = "C:\\Users\\lapto\\Desktop\\stage_project_old\\uploads\\procedures\\report_"
                    + savedProcedure.getId() + ".pdf";
            Files.write(Paths.get(reportPath), report);

            savedProcedure.setPathInitialProcedure(reportPath);
            savedProcedure.setPathBesoin(filePath.toString());
            repository.save(savedProcedure);
            plan.save(paa);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return savedProcedure;
    }
}
