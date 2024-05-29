package org.sid.entites.DTO;

import lombok.Data;

@Data
public class DossierDTO {

    private String reference;
    private Integer idPaa;
    private int fkIduser;

    public DossierDTO(String reference, Integer idPaa,int fkIduser) {
        this.reference = reference;
        this.idPaa = idPaa;
        this.fkIduser=fkIduser;

    }

    public DossierDTO() {
    }
}

