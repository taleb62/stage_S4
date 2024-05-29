package org.sid.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.nio.file.Path;
import java.sql.Timestamp;

@Entity
@Table(name = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDB implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idElm;
    private int fkIdTbl;
    private String name;
    private String fileNameOnDisc;
    private String fileSubFolder;
    private Timestamp dtUpload =new Timestamp(new java.util.Date().getTime());
    private int fkIduser;



    public FileDB(String fileName, String fileNameOnDisc, String fileSubFolder, Integer idPaa, Integer fkIduser,int fkIdTbl) {
        this.name=fileName;
        this.fileNameOnDisc=fileNameOnDisc;
        this.fileSubFolder=fileSubFolder;
        this.idElm=idPaa;
        this.fkIduser=fkIduser;
        this.fkIdTbl=fkIdTbl;
    }
}
