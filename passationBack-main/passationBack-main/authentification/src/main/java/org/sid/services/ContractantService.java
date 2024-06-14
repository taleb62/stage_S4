package org.sid.services;


import org.sid.dao.ContractantRepository;
import org.sid.entites.Contractant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ContractantService {

    @Autowired
    private ContractantRepository contractantRepository;

    public List<Contractant> getAllContractants() {
        return contractantRepository.findAll();
    }

    public Optional<Contractant> getContractantById(Long id) {
        return contractantRepository.findById(id);
    }

    public Contractant saveContractant(Contractant contractant) {
        return contractantRepository.save(contractant);
    }

    public void deleteContractant(Long id) {
        contractantRepository.deleteById(id);
    }
}
