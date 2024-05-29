package org.sid.dao;

import org.sid.entites.Dossier;
import org.sid.entites.Plis;
import org.sid.entites.plan_anuell_achat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlisRepository extends JpaRepository<Plis,Integer> {

    @Query(value = "select p from Plis p where p.idDossier=?1")
    List<Plis> getPlis(Integer id);
}
