package org.sid.entites.DTO;

import java.time.LocalDate;

public class ProcedureRequest {
    private Integer Idpaa;
    private String origin;
    private double montant;
    private String destinataire;
    private String sourceFinanciere;
    private String description;
    private LocalDate deadlineEstime;

    // Getters et setters
    public Integer getIdpaa() {
        return Idpaa;
    }

    public void setIdpaa(Integer idpaa) {
        Idpaa = idpaa;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getSourceFinanciere() {
        return sourceFinanciere;
    }

    public void setSourceFinanciere(String sourceFinanciere) {
        this.sourceFinanciere = sourceFinanciere;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadlineEstime() {
        return deadlineEstime;
    }

    public void setDeadlineEstime(LocalDate deadlineEstime) {
        this.deadlineEstime = deadlineEstime;
    }
}
