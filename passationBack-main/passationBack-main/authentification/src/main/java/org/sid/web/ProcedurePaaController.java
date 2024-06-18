package org.sid.web;

import java.util.List;
import java.util.Optional;

import org.sid.dao.planRepository;
import org.sid.entites.ProcedurePaa;
import org.sid.entites.plan_anuell_achat;
import org.sid.entites.DTO.ProcedureRequest;
import org.sid.services.ProcedurePaaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rest/procedure")
@CrossOrigin("*")
public class ProcedurePaaController {

    @Autowired
    private ProcedurePaaService service;

    @Autowired
    planRepository paaRepository;

    @GetMapping
    public List<ProcedurePaa> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcedurePaa> getById(@PathVariable Integer id) {
        Optional<ProcedurePaa> procedurePaa = service.findById(id);
        return procedurePaa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value =  "/{paaid}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    
    public List<ProcedurePaa> create(@PathVariable Integer paaid,
            @RequestBody ProcedureRequest procedureRequest) {
        plan_anuell_achat paa = paaRepository.findById(paaid)
                .orElseThrow(() -> new RuntimeException("PAA not found"));

        List<ProcedurePaa> pro = paa.getProcedures();

        return pro;
        // service.createProcedure(
        // paaid,
        // procedureRequest.getOrigin(),
        // procedureRequest.getDestinataire(),
        // procedureRequest.getSourceFinanciere(),
        // procedureRequest.getDescription(),
        // procedureRequest.getDeadlineEstime(),
        // procedureRequest.getMontant());
        // return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcedurePaa> update(@PathVariable Integer id, @RequestBody ProcedurePaa procedurePaa) {
        Optional<ProcedurePaa> existingProcedurePaa = service.findById(id);
        if (existingProcedurePaa.isPresent()) {
            procedurePaa.setId(id);
            return ResponseEntity.ok(service.save(procedurePaa));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // @PostMapping()

    // public ProcedurePaa createprocedure(@RequestBody Integer Idpaa , @RequestBody
    // String origin, @RequestBody Double montant ) {

    // }
}
