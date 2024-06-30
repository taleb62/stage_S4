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

    private boolean LettreCree = false; // Correct field name (if it should be lowercase, update it)
    private boolean ouvert = true;

    @ManyToOne
    @JoinColumn(name = "idPaa", insertable=false ,updatable=false)
    private plan_anuell_achat paa;

    @ManyToOne
    @JoinColumn(name = "procedure_id", insertable=false ,updatable=false)
    private ProcedurePaa procedurePaa;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false ,updatable=false)
    private ProcedurePaa user;



    

}
