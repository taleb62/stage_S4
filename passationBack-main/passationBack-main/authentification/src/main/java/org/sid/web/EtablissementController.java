package org.sid.web;

import org.sid.entites.Etablissement;
import org.sid.services.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/rest/etablissements", produces = MediaType.APPLICATION_JSON_VALUE)
public class EtablissementController {

    @Autowired
    private EtablissementService service;

    @GetMapping
    public List<Etablissement> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etablissement> getById(@PathVariable Integer id) {
        Optional<Etablissement> etablissement = service.findById(id);
        return etablissement.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Etablissement create(@RequestBody Etablissement etablissement) {
        return service.save(etablissement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etablissement> update(@PathVariable Integer id, @RequestBody Etablissement etablissement) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        etablissement.setId(id);
        return ResponseEntity.ok(service.save(etablissement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
