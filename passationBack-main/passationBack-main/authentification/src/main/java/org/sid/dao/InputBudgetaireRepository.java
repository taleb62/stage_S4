package org.sid.dao;

import org.sid.entites.InputBudgetaire;
import org.sid.entites.mod_passation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InputBudgetaireRepository extends JpaRepository<InputBudgetaire, Long> {
    InputBudgetaire findTopByYearAndTypeSelectionOrderByUniqueIdDesc(int year, mod_passation typeSelection);

    InputBudgetaire findByBudgetNumber(String budgetNumber);
}
