package org.sid.dao;

import org.sid.entites.AppUser;
import org.sid.entites.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, Integer> {
    @Transactional
    @Query(value="SELECT *FROM files where id_elm=?1 and fk_iduser=?2",nativeQuery=true)
    Stream<FileDB> getListdocument(Integer idPaa,Integer fkIduser);
    //Stream<FileDB> findAllByIdElmAndFkIduser(Integer idPaa,Integer fkIduser);


    @Query(value = "SELECT g.*, u.nom from files g left JOIN appuser u on u.id = g.id WHERE g.id_elm=:idElem and g.fk_iduser=:fkIduser and g.fk_id_tbl=:fkIdTbl"
            , nativeQuery = true)
    List<Map<String,Object>> findAllByIdElemAndFkIdTblAndCodeAndFileName(
            @Param("idElem") Integer idElem,
            @Param("fkIduser") Integer fkIduser,
            @Param("fkIdTbl") int fkIdTbl
        );


    @Query(value="SELECT *FROM files where id_elm=?1 and fk_id_tbl=?2",nativeQuery=true)
    List<FileDB> getdocumentPaa(Integer idPaa,Integer fkIdTbl);
}


