package org.sid.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PLAN_ANUELL_ACHAT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class plan_anuell_achat implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String objetDepense;
        private String inpuBudgetaire;
        private Integer fkidTypeMarche;
        private Integer fkidModPassation;

        private Double mntEstimatif;

        private Double montantRestant;

        private LocalDate datePreviLancement;
        private LocalDate datePreviAttribution;
        private Timestamp dateCrationProcedure;

        private String origine;
        private String destinataire;

        private boolean enprocedure = false;
        private boolean dosssierCree = false;
        private boolean isvalidated = false;

        @ManyToOne
        @JoinColumns({ @JoinColumn(name = "fkidTypeMarche", referencedColumnName = "id", nullable = false, updatable = false, insertable = false) })
        private type_marche typeMarche;

        @ManyToOne
        @JoinColumns({ @JoinColumn(name = "fkidModPassation", referencedColumnName = "id", nullable = false, updatable = false, insertable = false) })
        private mod_passation modPassation;



        @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumns({
                        @JoinColumn(name = "idElm", referencedColumnName = "id")

        })
        private List<FileDB> fileDB;

        // @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paa")

        // private List<ProcedurePaa> procedures;

        @ManyToOne
        @JoinColumn(name = "etablissement_id")
        private Etablissement etablissement;

        public void setMontantRestant(Double montantRestant) {
                this.montantRestant = montantRestant;
        }

}
