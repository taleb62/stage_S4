package org.sid.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "VERDICT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Verdict implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "procedure_id", nullable = false)
    private ProcedurePaa procedure;

    @ManyToOne(optional = false) // Ensure this is set to false if it is a required field
    @JoinColumn(name = "contractant_id", nullable = false)
    private Contractant contractant;

    @ManyToOne
    @JoinColumn(name = "type_marche_id", nullable = false)
    private type_marche typeMarche;
}
