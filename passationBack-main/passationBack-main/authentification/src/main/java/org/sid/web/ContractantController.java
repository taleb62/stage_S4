package org.sid.web;


import org.sid.entites.Contractant;
import org.sid.services.ContractantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rest/contractants")
@CrossOrigin("*")
public class ContractantController {

    @Autowired
    private ContractantService contractantService;

    @GetMapping
    public ResponseEntity<List<Contractant>> getAllContractants() {
        List<Contractant> contractants = contractantService.getAllContractants();
        return new ResponseEntity<>(contractants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contractant> getContractantById(@PathVariable Long id) {
        Optional<Contractant> contractant = contractantService.getContractantById(id);
        return contractant.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Contractant> createContractant(@RequestBody Contractant contractant) {
        Contractant newContractant = contractantService.saveContractant(contractant);
        return new ResponseEntity<>(newContractant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contractant> updateContractant(@PathVariable Long id, @RequestBody Contractant contractantDetails) {
        Optional<Contractant> contractant = contractantService.getContractantById(id);

        if (contractant.isPresent()) {
            Contractant existingContractant = contractant.get();
            existingContractant.setCompanyName(contractantDetails.getCompanyName());
            existingContractant.setContactInfo(contractantDetails.getContactInfo());
            Contractant updatedContractant = contractantService.saveContractant(existingContractant);
            return new ResponseEntity<>(updatedContractant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteContractant(@PathVariable Long id) {
        contractantService.deleteContractant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
