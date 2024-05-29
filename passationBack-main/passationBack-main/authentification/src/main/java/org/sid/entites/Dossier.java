package org.sid.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "DOSSIER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dossier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String reference;
    private Integer idPaa;
    private int fkIduser;
    private boolean LettreCree=false;

    public Dossier(String reference, Integer idPaa,int fkIduser) {
        this.reference=reference;
        this.idPaa=idPaa;
        this.fkIduser=fkIduser;
    }
}
