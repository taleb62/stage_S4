package org.sid.entites.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DossierFileDTO {
    private Integer id;
    private String reference;
    private Integer idPaa;
    private boolean LettreCree;
    private String objetDepense;
    private String inpuBudgetaire;
    private Integer idElm;
//    private Timestamp dtUpload;
    private String name;
    private String fileNameOnDisc;
    private String fileSubFolder;


//    public DossierFileDTO() {
//
//    }
//
//    public DossierFileDTO(Integer id, String reference, Integer idElm, Timestamp dtUpload, String name, String fileNameOnDisc, String fileSubFolder) {
//        this.id = id;
//        this.reference = reference;
//        this.idElm = idElm;
//        this.dtUpload = dtUpload;
//        this.name = name;
//        this.fileNameOnDisc = fileNameOnDisc;
//        this.fileSubFolder = fileSubFolder;
//    }


}
