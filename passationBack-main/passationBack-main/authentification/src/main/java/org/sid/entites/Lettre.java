package org.sid.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "LETTRE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Lettre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomFournissuer;
    private String nonAutoriteContractante;
    private Timestamp dateLimiteDepot;
    private Timestamp dateOverturePlis;
    private Timestamp dateAnterieurDepot;
    private String lieuOverturePlis;
    private Integer idDossier;
    private int fkIduser;




    public Lettre(String nomFournissuer, String nonAutoriteContractante, Timestamp dateLimiteDepot, Timestamp dateOverturePlis,
                  Timestamp dateAnterieurDepot, String lieuOverturePlis, Integer idDossier, int fkIduser) {
        this.nomFournissuer=nomFournissuer;
        this.nonAutoriteContractante=nonAutoriteContractante;
        this.dateLimiteDepot=dateLimiteDepot;
        this.dateOverturePlis=dateOverturePlis;
        this.dateAnterieurDepot=dateAnterieurDepot;
        this.lieuOverturePlis=lieuOverturePlis;
        this.idDossier=idDossier;
        this.fkIduser=fkIduser;
    }
}
