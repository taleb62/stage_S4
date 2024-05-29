package org.sid.entites.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PlisDTO {
    private Timestamp dateReception;
    private Integer posteOuCourrierExpress;
    private String porteur;
    private String observation;
    private Integer idDossier;
    private Integer idLettre;
}
