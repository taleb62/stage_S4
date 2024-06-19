package org.sid.web;

import org.sid.entites.Verdict;
import org.sid.services.VerdictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest/verdicts")
public class VerdictController {

    private final VerdictService verdictService;

    @Autowired
    public VerdictController(VerdictService verdictService) {
        this.verdictService = verdictService;
    }

    @GetMapping
    public ResponseEntity<List<Verdict>> getAllVerdicts() {
        List<Verdict> verdicts = verdictService.getAllVerdicts();
        return ResponseEntity.ok(verdicts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Verdict> getVerdictById(@PathVariable Integer id) {
        Verdict verdict = verdictService.getVerdictById(id);
        return verdict != null ? ResponseEntity.ok(verdict) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Verdict> createVerdict(@RequestBody Verdict verdict) {
        if (verdict.getContractant() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Verdict savedVerdict = verdictService.createVerdict(verdict);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVerdict);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Verdict> updateVerdict(@PathVariable Integer id, @RequestBody Verdict verdict) {
        Verdict updatedVerdict = verdictService.updateVerdict(id, verdict);
        return updatedVerdict != null ? ResponseEntity.ok(updatedVerdict) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVerdict(@PathVariable Integer id) {
        verdictService.deleteVerdict(id);
        return ResponseEntity.noContent().build();
    }
}
