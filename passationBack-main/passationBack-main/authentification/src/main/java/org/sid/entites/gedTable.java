package org.sid.entites;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "ged_table" )
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class gedTable {
    private int idTbl;
    private String codeTbl;

    @Id
    @Column(name = "idTbl", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdTbl() {
        return idTbl;
    }

    public void setIdTbl(int idTbl) {
        this.idTbl = idTbl;
    }

    @Basic
    @Column(name = "codeTbl", nullable = false, length = 145)
    public String getCodeTbl() {
        return codeTbl;
    }

    public void setCodeTbl(String codeTbl) {
        this.codeTbl = codeTbl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        gedTable that = (gedTable) o;
        return idTbl == that.idTbl &&
                Objects.equals(codeTbl, that.codeTbl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTbl, codeTbl);
    }
}
