package org.sid.services;


import org.sid.dao.EtablissementRepository;
import org.sid.entites.Etablissement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtablissementService {

    @Autowired
    private EtablissementRepository repository;

    public List<Etablissement> findAll() {
        return repository.findAll();
    }

    public Optional<Etablissement> findById(Integer id) {
        return repository.findById(id);
    }

    public Etablissement save(Etablissement etablissement) {
        return repository.save(etablissement);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
