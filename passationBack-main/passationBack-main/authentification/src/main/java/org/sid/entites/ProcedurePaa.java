package org.sid.entites;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PROCEDUREPAA")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProcedurePaa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String origin;
    private String destinataire;
    private String sourceFinanciere;
    private String description;
    private LocalDate deadlineEstime;
    private double montant;
    private String pathInitialProcedure;
    private String pathBesoin;

    @ManyToOne
    @JoinColumn(name = "paaid")
    private plan_anuell_achat paa;

    @OneToMany(mappedBy = "procedure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Verdict> verdicts;

}
