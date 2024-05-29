package org.sid.entites.DTO;

import lombok.Data;
import org.sid.entites.AppRole;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Data
public class LettreDTO {
    //private String nomFournissuer;
    private Collection nomFournissuer = new ArrayList<>();
    private String nonAutoriteContractante;
    private Timestamp dateLimiteDepot;
    private Timestamp dateOverturePlis;
    private Timestamp dateAnterieurDepot;
    private String LieuOverturePlis;
    private Integer IdDossier;
    private int fkIduser;
}
