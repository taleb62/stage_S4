package org.sid.entites.DTO;

import java.sql.Timestamp; // Ajout de l'import
import java.time.LocalDate; // Ajout de l'import

import org.sid.entites.AppUser;

import lombok.Data;

@Data
public
class PaaFormProcedure{
    private Integer id; // Ajoutez tous les champs nécessaires pour la création d'un PAA
    private String objetDepense;
    private String inpuBudgetaire;
    private Integer fkidTypeMarche;
    private Integer fkidModPassation;
    private Double mntEstimatif;
    private Integer montantrestant;
    private LocalDate datePreviLancement;
    private LocalDate datePreviAttribution;
    private Timestamp dateCrationProcedure;
    private String origine;
    private String destinataire;
    private Integer userId;

    public void setMntEstimatif(Double mntEstimatif) {
        this.mntEstimatif = mntEstimatif;
    }

    public void updateMontantRestant() {
        this.montantrestant = this.mntEstimatif.intValue();
    }
}