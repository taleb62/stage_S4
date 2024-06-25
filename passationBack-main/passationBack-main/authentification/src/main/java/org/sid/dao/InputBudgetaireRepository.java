package org.sid.dao;

import java.util.List;

import org.sid.entites.InputBudgetaire;
import org.sid.entites.mod_passation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InputBudgetaireRepository extends JpaRepository<InputBudgetaire, Long> {
    InputBudgetaire findTopByYearAndTypeSelectionOrderByUniqueIdDesc(int year, mod_passation typeSelection);

    InputBudgetaire findByBudgetNumber(String budgetNumber);

    @Query("SELECT ib FROM InputBudgetaire ib WHERE ib.etablissement IS NOT NULL")
    List<InputBudgetaire> findAllWithEtablissement();

}
