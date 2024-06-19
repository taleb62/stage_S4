package org.sid.entites.DTO;

import java.time.LocalDate; // Ajout de l'import

import lombok.Data;

import java.sql.Timestamp; // Ajout de l'import

@Data
public
class PaaFormProcedure{
    private Integer id; // Ajoutez tous les champs nécessaires pour la création d'un PAA
    private String objetDepense;
    private String inpuBudgetaire;
    private Integer fkidTypeMarche;
    private Integer fkidModPassation;
    private Double mntEstimatif;
    private LocalDate datePreviLancement;
    private LocalDate datePreviAttribution;
    private Timestamp dateCrationProcedure;
    private String origine;
    private String destinataire;
}