package org.sid.services;

import org.sid.dao.VerdictRepository;
import org.sid.entites.Verdict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VerdictServiceImpl implements VerdictService {

    private final VerdictRepository verdictRepository;

    @Autowired
    public VerdictServiceImpl(VerdictRepository verdictRepository) {
        this.verdictRepository = verdictRepository;
    }

    @Override
    public List<Verdict> getAllVerdicts() {
        return verdictRepository.findAll();
    }

    @Override
    public Verdict getVerdictById(Integer id) {
        Optional<Verdict> optionalVerdict = verdictRepository.findById(id);
        return optionalVerdict.orElse(null);
    }

    @Override
    public Verdict createVerdict(Verdict verdict) {
        if (verdict.getContractant() == null) {
            throw new IllegalArgumentException("Contractant must not be null");
        }
        return verdictRepository.save(verdict);
    }

    @Override
    public Verdict updateVerdict(Integer id, Verdict verdict) {
        if (verdictRepository.existsById(id)) {
            verdict.setId(id);
            return verdictRepository.save(verdict);
        }
        return null;
    }

    @Override
    public void deleteVerdict(Integer id) {
        if (verdictRepository.existsById(id)) {
            verdictRepository.deleteById(id);
        }
    }
}
