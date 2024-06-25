package org.sid.entites;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "input_budgetaire")
public class InputBudgetaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "budget_number", unique = true, nullable = false)
    private String budgetNumber;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "unique_id", nullable = false)
    private int uniqueId;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @ManyToOne()
    @JoinColumn(name = "etablissement_id", nullable = false)
    private Etablissement etablissement;

    
    @ManyToOne()
    @JoinColumn(name = "type_selection", nullable = false)
    private mod_passation typeSelection;
    @ManyToOne()
    @JoinColumn(name = "type_de_marcher", nullable = false)
    private type_marche typeMarcher;
    // Constructors, getters, and setters
    public InputBudgetaire() {
    }
    public InputBudgetaire( String budgetNumber, int year, int uniqueId, LocalDate createdDate,
            Etablissement etablissement, mod_passation typeSelection, type_marche typeMarcher) {
        this.id = id;
        this.budgetNumber = budgetNumber;
        this.year = year;
        this.uniqueId = uniqueId;
        this.createdDate = createdDate;
        this.etablissement = etablissement;
        this.typeSelection = typeSelection;
        this.typeMarcher = typeMarcher;
    }

    
}
