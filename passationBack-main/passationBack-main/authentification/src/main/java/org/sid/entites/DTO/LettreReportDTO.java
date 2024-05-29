package org.sid.entites.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

@Data
public class LettreReportDTO {
    private Integer id;
    private String nomFournissuer;
    private String nonAutoriteContractante;
    private Date dateLimiteDepot;
    private Timestamp dateOverturePlis;
    private Timestamp dateAnterieurDepot;
    private String lieuOverturePlis;
    private String reference;
    private String objetDepense;

    public LettreReportDTO() {
    }

    public LettreReportDTO(Integer id, String nomFournissuer, String nonAutoriteContractante, Date dateLimiteDepot,
                            String lieuOverturePlis,
                           String reference, String objetDepense) {
        this.id=id;
        this.nomFournissuer = nomFournissuer;
        this.nonAutoriteContractante = nonAutoriteContractante;
        this.dateLimiteDepot = dateLimiteDepot;
//        this.dateOverturePlis = dateOverturePlis;
//        this.dateAnterieurDepot = dateAnterieurDepot;
        this.lieuOverturePlis = lieuOverturePlis;
        this.reference = reference;
        this.objetDepense = objetDepense;
    }
}
