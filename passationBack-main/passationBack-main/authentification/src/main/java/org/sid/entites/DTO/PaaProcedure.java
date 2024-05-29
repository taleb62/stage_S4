package org.sid.entites.DTO;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Data
public class PaaProcedure {
    private Integer id;
    private String origine;
    private String destinataire;
    private String objetDepense;
    private LocalDate datePreviLancement;
    private LocalDate datePreviAttribution;
    private String inpuBudgetaire;
    private Double mntEstimatif;
    private String modePassation;

//    private String name;
//    private String fileNameOnDisc;
//    //private Date dtUpload;
//    private int fkIdTbl;

//    public PaaProcedure(Integer id, String origine, String destinataire, String objetDepense, LocalDate datePreviLancement, LocalDate datePreviAttribution, String inpuBudgetaire, Double mntEstimatif, String modePassation, String name, String fileNameOnDisc, int fkIdTbl) {
//        this.id = id;
//        this.origine = origine;
//        this.destinataire = destinataire;
//        this.objetDepense = objetDepense;
//        this.datePreviLancement = datePreviLancement;
//        this.datePreviAttribution = datePreviAttribution;
//        this.inpuBudgetaire = inpuBudgetaire;
//        this.mntEstimatif = mntEstimatif;
//        this.modePassation = modePassation;
//        this.name = name;
//        this.fileNameOnDisc = fileNameOnDisc;
//        //this.dtUpload = dtUpload;
//        this.fkIdTbl=fkIdTbl;
//
//    }

    public PaaProcedure() {
    }

    public PaaProcedure(Integer id, String origine, String destinataire, String objetDepense, LocalDate datePreviLancement, LocalDate datePreviAttribution, String inpuBudgetaire, Double mntEstimatif, String modePassation) {
        this.id = id;
        this.origine = origine;
        this.destinataire = destinataire;
        this.objetDepense = objetDepense;
        this.datePreviLancement = datePreviLancement;
        this.datePreviAttribution = datePreviAttribution;
        this.inpuBudgetaire = inpuBudgetaire;
        this.mntEstimatif = mntEstimatif;
        this.modePassation = modePassation;
    }
}
