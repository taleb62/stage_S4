package org.sid.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "MOD_PASSATION")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class mod_passation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String modePassation;

    // @OneToMany(mappedBy = "typeSelection", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<InputBudgetaire> inputBudgetaires;
}
