package org.sid.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@Table(name = "PLIS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Plis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idplis;
    private Timestamp dateReception;
    private Integer posteOuCourrierExpress;
    private String porteur;
    private String observation;
    private Integer idDossier;
    @Column(name = "id_lettre")
    private Integer idLettre;


    public Plis(Timestamp dateReception, Integer posteOuCourrierExpress, String porteur, String observation, Integer idDossier, Integer idLettre) {
        this.dateReception = dateReception;
        this.posteOuCourrierExpress = posteOuCourrierExpress;
        this.porteur = porteur;
        this.observation = observation;
        this.idDossier = idDossier;
        this.idLettre = idLettre;
    }
}
